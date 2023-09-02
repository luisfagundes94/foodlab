package com.luisfagundes.recipes

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.luisfagundes.domain.factory.FakeRecipeFactory
import com.luisfagundes.domain.usecases.GetRecipeDetails
import com.luisfagundes.framework.decoder.StringDecoder
import com.luisfagundes.recipes.details.RecipeDetailsViewModel
import com.luisfagundes.testing.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import com.luisfagundes.framework.network.Result
import com.luisfagundes.recipes.details.RecipeDetailsUiState
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

private const val FAKE_ID = "1"

@ExperimentalCoroutinesApi
class RecipeDetailsViewModelTest {

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
    }

    @Test
    fun `uiState emits Loading when result is Loading`() = runTest {
        // Given
        coEvery { getRecipeDetails.invoke(params) } returns flowOf(Result.Loading)

        // When
        viewModel = RecipeDetailsViewModel(
            getRecipeDetails = getRecipeDetails,
            stringDecoder = stringDecoder,
            savedStateHandle = savedStateHandle,
        )

        // Then
        viewModel.uiState.test {
            assertEquals(awaitItem(), RecipeDetailsUiState.Loading)
            expectNoEvents()
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `uiState emits Error when result is Error`() = runTest {
        // Given
        coEvery { getRecipeDetails.invoke(params) } returns flowOf(Result.Error(Exception()))

        // When
        viewModel = RecipeDetailsViewModel(
            getRecipeDetails = getRecipeDetails,
            stringDecoder = stringDecoder,
            savedStateHandle = savedStateHandle,
        )

        // Then
        viewModel.uiState.test {
            assertEquals(awaitItem(), RecipeDetailsUiState.Error)
            expectNoEvents()
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `uiState emits Success when result is Success`() = runTest {
        // Given
        val recipe = FakeRecipeFactory.recipe
        coEvery { getRecipeDetails.invoke(params) } returns flowOf(Result.Success(recipe))

        // When
        viewModel = RecipeDetailsViewModel(
            getRecipeDetails = getRecipeDetails,
            stringDecoder = stringDecoder,
            savedStateHandle = savedStateHandle,
        )

        // Then
        viewModel.uiState.test {
            assertEquals(awaitItem(), RecipeDetailsUiState.Success(recipe))
            expectNoEvents()
            cancelAndIgnoreRemainingEvents()
        }
    }
}