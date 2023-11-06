package com.luisfagundes.home

import app.cash.turbine.test
import com.luisfagundes.domain.factory.FakeRecipeFactory
import com.luisfagundes.domain.repositories.RecipeRepository
import com.luisfagundes.domain.usecases.GetRecipeSections
import com.luisfagundes.framework.network.Result
import com.luisfagundes.home.presentation.HomeUiState
import com.luisfagundes.home.presentation.HomeViewModel
import com.luisfagundes.testing.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    private val getRecipeSections: GetRecipeSections = mockk()
    private val repository: RecipeRepository = mockk()
    private lateinit var viewModel: HomeViewModel

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun `uiState emits Loading when result is Loading`() = runTest {
        // Given
        coEvery { getRecipeSections.invoke() } returns flowOf(Result.Loading)

        // When
        viewModel = HomeViewModel(getRecipeSections, repository)

        // Then
        viewModel.uiState.test {
            assertEquals(awaitItem(), HomeUiState.Loading)
            expectNoEvents()
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `uiState emits Error when result is Error`() = runTest {
        // Given
        coEvery { getRecipeSections.invoke() } returns flowOf(
            Result.Loading,
            Result.Error(Exception())
        )

        // When
        viewModel = HomeViewModel(getRecipeSections, repository)

        // Then
        viewModel.uiState.test {
            assertEquals(awaitItem(), HomeUiState.Loading)
            assertEquals(awaitItem(), HomeUiState.Error)
            expectNoEvents()
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `uiState emits Success when result is Success`() = runTest {
        // Given
        val recipeSections = FakeRecipeFactory.sections
        coEvery { getRecipeSections.invoke() } returns flowOf(
            Result.Loading,
            Result.Success(recipeSections)
        )

        // When
        viewModel = HomeViewModel(getRecipeSections, repository)

        // Then
        viewModel.uiState.test {
            assertEquals(awaitItem(), HomeUiState.Loading)
            assertEquals(awaitItem(), HomeUiState.Success(recipeSections))
            cancelAndIgnoreRemainingEvents()
        }
    }
}
