package com.luisfagundes.recipes.viewModels

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.luisfagundes.domain.factory.FakeRecipeFactory
import com.luisfagundes.domain.usecases.GetRecipeList
import com.luisfagundes.framework.decoder.StringDecoder
import com.luisfagundes.recipes.list.presentation.RecipeListViewModel
import com.luisfagundes.testing.MainDispatcherRule
import com.luisfagundes.framework.network.Result
import com.luisfagundes.recipes.list.presentation.RecipeListUiState
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

private const val FAKE_ID = "1"

@ExperimentalCoroutinesApi
class RecipeListViewModelTest {

    private val getRecipeList = mockk<GetRecipeList>()
    private val stringDecoder = mockk<StringDecoder>()
    private val savedStateHandle = mockk<SavedStateHandle>()

    private lateinit var viewModel: RecipeListViewModel

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setUp() {
        coEvery { stringDecoder.decode(any()) } returns FAKE_ID
        coEvery { savedStateHandle.get<String>(any()) } returns FAKE_ID

        viewModel = RecipeListViewModel(
            getRecipeList,
            stringDecoder,
            savedStateHandle
        )

    }

    @Test
    fun `fetchRecipeList emits Loading then Success state`() = runTest {
        // Arrange
        val fakeRecipes = listOf(
            FakeRecipeFactory.recipe,
            FakeRecipeFactory.recipe,
            FakeRecipeFactory.recipe,
        )
        coEvery { getRecipeList.invoke(any()) } returns Result.Success(fakeRecipes)

        // Assert
        viewModel.uiState.test {
            assertEquals(RecipeListUiState.Idle, awaitItem())

            // Act
            viewModel.fetchRecipeList()

            assertEquals(RecipeListUiState.Loading, awaitItem())
            assertEquals(RecipeListUiState.Success(fakeRecipes), awaitItem())

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `fetchRecipeList emits Loading then Error state`() = runTest {
        // Arrange
        coEvery { getRecipeList.invoke(any()) } returns Result.Error(Exception("Test exception"))

        // Assert
        viewModel.uiState.test {
            assertEquals(RecipeListUiState.Idle, awaitItem())

            // Act
            viewModel.fetchRecipeList()

            assertEquals(RecipeListUiState.Loading, awaitItem())
            assertEquals(RecipeListUiState.Error, awaitItem())

            cancelAndIgnoreRemainingEvents()
        }
    }

    // More tests...
}
