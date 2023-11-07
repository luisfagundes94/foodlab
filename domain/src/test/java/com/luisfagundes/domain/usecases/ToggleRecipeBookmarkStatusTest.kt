package com.luisfagundes.domain.usecases

import com.luisfagundes.domain.factory.FakeRecipeFactory
import com.luisfagundes.domain.repositories.RecipeRepository
import com.luisfagundes.testing.MainDispatcherRule
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import com.luisfagundes.framework.network.Result
import io.mockk.coEvery
import io.mockk.coVerify
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ToggleRecipeBookmarkStatusTest {

    private val repository: RecipeRepository = mockk()

    private lateinit var toggleRecipeBookmarkStatus: ToggleRecipeBookmarkStatus

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setUp() {
        toggleRecipeBookmarkStatus = ToggleRecipeBookmarkStatus(
            repository,
            dispatcher = mainDispatcherRule.testDispatcher
        )
    }

    @Test
    fun `invoke with null recipe should return error`() = runTest {
        // Given
        val params = ToggleRecipeBookmarkStatus.Params(recipe = null)

        // When
        val result = toggleRecipeBookmarkStatus(params)

        // Then
        assert(result is Result.Error)
        assertEquals("Recipe must not be null", (result as Result.Error).exception?.message)
    }

    @Test
    fun `invoke with not bookmarked should save recipe`() = runTest {
        // Given
        val recipe = FakeRecipeFactory.recipe.copy(bookmarked = false)
        val params = ToggleRecipeBookmarkStatus.Params(recipe)

        coEvery { repository.saveRecipe(recipe) } returns Result.Success(Unit)

        // When
        toggleRecipeBookmarkStatus(params)

        // Then
        coVerify(exactly = 1) { repository.saveRecipe(recipe) }
    }

    @Test
    fun `invoke with bookmarked should delete recipe`() = runTest {
        // Given
        val recipe = FakeRecipeFactory.recipe
        val params = ToggleRecipeBookmarkStatus.Params(recipe)

        coEvery { repository.deleteRecipe(recipe) } returns Result.Success(Unit)

        // When
        toggleRecipeBookmarkStatus(params)

        // Then
        coVerify(exactly = 1) { repository.deleteRecipe(recipe) }
    }
}