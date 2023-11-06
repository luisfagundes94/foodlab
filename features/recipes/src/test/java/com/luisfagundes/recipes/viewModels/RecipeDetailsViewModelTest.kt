package com.luisfagundes.recipes.viewModels

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.luisfagundes.domain.factory.FakeRecipeFactory
import com.luisfagundes.domain.repositories.RecipeRepository
import com.luisfagundes.domain.usecases.GetRecipeDetails
import com.luisfagundes.framework.decoder.StringDecoder
import com.luisfagundes.framework.network.Result
import com.luisfagundes.recipes.details.presentation.RecipeDetailsUiState
import com.luisfagundes.recipes.details.presentation.RecipeDetailsViewModel
import com.luisfagundes.testing.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

private const val FAKE_ID = "1"

@ExperimentalCoroutinesApi
class RecipeDetailsViewModelTest {

    private val repository: RecipeRepository = mockk()
    private val getRecipeDetails: GetRecipeDetails = mockk()
    private val stringDecoder: StringDecoder = mockk()
    private val savedStateHandle: SavedStateHandle = mockk()

    private lateinit var viewModel: RecipeDetailsViewModel

    private val params = GetRecipeDetails.Params(FAKE_ID.toInt())

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setUp() {
        coEvery { stringDecoder.decode(any()) } returns "1"
        coEvery { savedStateHandle.get<String>(any()) } returns FAKE_ID

        viewModel = RecipeDetailsViewModel(
            getRecipeDetails = getRecipeDetails,
            stringDecoder = stringDecoder,
            savedStateHandle = savedStateHandle,
            repository = repository
        )
    }

    @Test
    fun `uiState emits Loading when result is Loading`() = runTest {
        // Given
        coEvery { getRecipeDetails.invoke(params) } returns flowOf(Result.Loading)

        // Then
        viewModel.uiState.test {
            assertEquals(awaitItem(), RecipeDetailsUiState.Idle)

            // When
            viewModel.refreshRecipeDetails()

            assertEquals(awaitItem(), RecipeDetailsUiState.Loading)

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `uiState emits Error when result is Error`() = runTest {
        // Given
        val exception = Exception("Test Exception")

        coEvery { getRecipeDetails.invoke(params) } returns flowOf(
            Result.Loading,
            Result.Error(exception)
        )

        // Then
        viewModel.uiState.test {
            assertEquals(awaitItem(), RecipeDetailsUiState.Idle)

            // When
            viewModel.refreshRecipeDetails()

            assertEquals(awaitItem(), RecipeDetailsUiState.Loading)
            assertEquals(awaitItem(), RecipeDetailsUiState.Error)

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `uiState emits Success when result is Success`() = runTest {
        // Given
        val recipe = FakeRecipeFactory.recipe

        coEvery { getRecipeDetails.invoke(params) } returns flowOf(
            Result.Loading,
            Result.Success(recipe)
        )

        // Then
        viewModel.uiState.test {
            assertEquals(awaitItem(), RecipeDetailsUiState.Idle)

            // When
            viewModel.refreshRecipeDetails()

            assertEquals(awaitItem(), RecipeDetailsUiState.Loading)
            assertEquals(awaitItem(), RecipeDetailsUiState.Success(recipe))

            cancelAndIgnoreRemainingEvents()
        }
    }
}