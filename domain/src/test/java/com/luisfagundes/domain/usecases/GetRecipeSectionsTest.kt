package com.luisfagundes.domain.usecases

import com.luisfagundes.domain.enums.RecipeSortingOptions
import com.luisfagundes.domain.factory.FakeRecipeFactory
import com.luisfagundes.domain.models.RecipeSections
import com.luisfagundes.framework.network.Result
import com.luisfagundes.domain.repositories.RecipeRepository
import com.luisfagundes.testing.MockkUnitTest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetRecipeSectionsTest : MockkUnitTest() {

    private val repository = mockk<RecipeRepository>(relaxed = true)
    private lateinit var getRecipeSections: GetRecipeSections

    override fun onCreate() {
        super.onCreate()
        getRecipeSections = GetRecipeSections(
            repository = repository,
            dispatcher = testCoroutineRule.testDispatcher
        )
    }


    @Test
    fun verifyExecution() = runTest {
        // Given
        val recipe = FakeRecipeFactory.recipe
        val popularRecipes = listOf(recipe, recipe, recipe)
        val healthyRecipes = listOf(recipe, recipe, recipe)
        val quickRecipes = listOf(recipe, recipe, recipe)

        coEvery {
            repository.getRecipeList(match { it.sort == RecipeSortingOptions.POPULAR.value })
        } returns Result.Success(popularRecipes)
        coEvery {
            repository.getRecipeList(match { it.sort == RecipeSortingOptions.HEALTHY.value })
        } returns Result.Success(healthyRecipes)
        coEvery {
            repository.getRecipeList(match { it.sort == RecipeSortingOptions.QUICK.value })
        } returns Result.Success(quickRecipes)

        // When
        val flow = getRecipeSections.invoke().toList()

        // Then
        coVerify {
            repository.getRecipeList(match { it.sort == RecipeSortingOptions.POPULAR.value })
            repository.getRecipeList(match { it.sort == RecipeSortingOptions.HEALTHY.value })
            repository.getRecipeList(match { it.sort == RecipeSortingOptions.QUICK.value })
        }

        assertEquals(Result.Loading, flow[0])
        assertEquals(
            Result.Success(
                RecipeSections(
                    popularRecipes = popularRecipes,
                    healthyRecipes = healthyRecipes,
                    quickRecipes = quickRecipes
                )
            ),
            flow[1]
        )
    }
}