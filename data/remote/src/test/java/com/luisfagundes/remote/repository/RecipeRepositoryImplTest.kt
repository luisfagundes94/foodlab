package com.luisfagundes.remote.repository

import androidx.paging.PagingConfig
import com.luisfagundes.domain.datasources.RecipeDataSource
import com.luisfagundes.domain.factory.FakeRecipeFactory
import com.luisfagundes.domain.usecases.GetFlowRecipeList
import com.luisfagundes.domain.usecases.GetRecipeList
import com.luisfagundes.recipe.domain.models.RecipeListBody
import com.luisfagundes.framework.network.Result
import com.luisfagundes.remote.paging.RecipePagingSource
import com.luisfagundes.remote.repositories.RecipeRepositoryImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

private const val SORT = "sort"

class RecipeRepositoryImplTest {

    private val recipeDataSource: RecipeDataSource = mockk()

    private lateinit var recipeRepository: RecipeRepositoryImpl

    @Before
    fun setUp() {
        recipeRepository = RecipeRepositoryImpl(recipeDataSource)
    }

    @Test
    fun `test getRecipeList returns expected result`() = runTest {
        // Given
        val recipes = listOf(
            FakeRecipeFactory.recipe,
            FakeRecipeFactory.recipe,
        )
        val expectedResult = Result.Success(recipes)
        val recipeListBody = RecipeListBody(
            0,
            totalResults = 20,
            results = recipes,
        )
        val params = GetRecipeList.Params(sort = "popular")

        coEvery { recipeDataSource.fetchRecipes(any()) } returns recipeListBody

        // When
        val result = recipeRepository.getRecipeList(params)

        // Then
        assertEquals(expectedResult, result)
        coVerify { recipeDataSource.fetchRecipes(any()) }
    }

    @Test
    fun `test getRecipeDetails returns expected result`() = runTest {
        // Given
        val expectedRecipe = FakeRecipeFactory.recipe
        val recipeId = 1

        coEvery { recipeDataSource.fetchRecipeDetails(recipeId) } returns expectedRecipe

        // When
        val result = recipeRepository.getRecipeDetails(recipeId)

        // Then
        assertTrue(result is Result.Success)
        assertEquals(expectedRecipe, (result as Result.Success).data)
        coVerify { recipeDataSource.fetchRecipeDetails(recipeId) }
    }

    @Test
    fun `test getFlowRecipeList returns RecipePagingSource`() = runTest {
        // Given
        val params = GetFlowRecipeList.Params(
            pagingConfig = PagingConfig(
                pageSize = 20,
                prefetchDistance = 5,
                enablePlaceholders = false,
            ),
            sort = "popular",
        )

        // When
        val result = recipeRepository.getFlowRecipeList(params)

        // Then
        assert(result is RecipePagingSource)
    }

    @Test
    fun `test getRecipeList with different params`() = runTest {
        // Given
        val params1 = GetRecipeList.Params(sort = "popular")
        val params2 = GetRecipeList.Params(sort = "time")

        coEvery { recipeDataSource.fetchRecipes(any()) } returns RecipeListBody(0, 0, listOf())

        // When
        recipeRepository.getRecipeList(params1)
        recipeRepository.getRecipeList(params2)

        // Then
        coVerify(exactly = 1) {
            recipeDataSource.fetchRecipes(match { it[SORT] == params1.sort })
        }
        coVerify(exactly = 1) {
            recipeDataSource.fetchRecipes(match { it[SORT] == params2.sort })
        }
    }

    @Test
    fun `test getRecipeList throws exception`() = runTest {
        // Given
        val params = GetRecipeList.Params(sort = "popular")

        coEvery { recipeDataSource.fetchRecipes(any()) } throws Exception()

        // When
        val result = recipeRepository.getRecipeList(params)

        // THEN
        assertTrue(result is Result.Error)
        assertTrue((result as Result.Error).exception is Exception)
    }

    @Test
    fun `test getRecipeDetails throws exception`() = runTest {
        // Given
        val recipeId = 1

        coEvery { recipeDataSource.fetchRecipeDetails(recipeId) } throws Exception()

        // When
        val result = recipeRepository.getRecipeDetails(recipeId)

        // Then
        assertTrue(result is Result.Error)
        assertTrue((result as Result.Error).exception is Exception)
    }

}
