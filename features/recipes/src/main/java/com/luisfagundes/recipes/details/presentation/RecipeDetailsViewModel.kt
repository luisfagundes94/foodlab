package com.luisfagundes.recipes.details.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.luisfagundes.domain.models.Recipe
import com.luisfagundes.domain.repositories.RecipeRepository
import com.luisfagundes.domain.usecases.GetRecipeDetails
import com.luisfagundes.domain.usecases.ToggleRecipeBookmarkStatus
import com.luisfagundes.framework.base.mvvm.BaseViewModel
import com.luisfagundes.framework.decoder.StringDecoder
import com.luisfagundes.framework.network.Result
import com.luisfagundes.recipes.details.navigation.RecipeDetailsArg
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RecipeDetailsViewModel @Inject constructor(
    private val getRecipeDetails: GetRecipeDetails,
    private val toggleRecipeBookmarkStatus: ToggleRecipeBookmarkStatus,
    stringDecoder: StringDecoder,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel() {

    private val recipeDetailsArg = RecipeDetailsArg(savedStateHandle, stringDecoder)
    private val recipeId = recipeDetailsArg.recipeId.toInt()

    private val _uiState = MutableStateFlow<RecipeDetailsUiState>(RecipeDetailsUiState.Idle)
    val uiState: StateFlow<RecipeDetailsUiState> = _uiState.asStateFlow()

    private val _recipeBookmarkEvent = MutableSharedFlow<Boolean>()
    val recipeBookmarkEvent: SharedFlow<Boolean> = _recipeBookmarkEvent.asSharedFlow()

    private val _isBookmarked = MutableStateFlow(false)
    val isBookmarked: StateFlow<Boolean> = _isBookmarked.asStateFlow()

    init {
        refreshRecipeDetails()
    }

    fun refreshRecipeDetails() = viewModelScope.launch {
        _uiState.emit(RecipeDetailsUiState.Loading)

        val params = GetRecipeDetails.Params(recipeId)

        getRecipeDetails(params).collect { result ->
            _uiState.value = setRecipeDetailsState(result)
        }
    }

    private fun setRecipeDetailsState(result: Result<Recipe>) =
        when (result) {
            is Result.Success -> {
                _isBookmarked.value = result.data.bookmarked
                RecipeDetailsUiState.Success(result.data)
            }

            is Result.Error -> RecipeDetailsUiState.Error
            is Result.Loading -> RecipeDetailsUiState.Loading
        }

    fun toggleBookmarkStatus(recipe: Recipe?) = safeLaunch {
        val params = ToggleRecipeBookmarkStatus.Params(recipe, _isBookmarked.value)
        val result = toggleRecipeBookmarkStatus.invoke(params)
        handleBookmarkResult(result)
    }

    private suspend fun handleBookmarkResult(result: Result<Unit>) {
        when (result) {
            is Result.Success -> {
                _recipeBookmarkEvent.emit(!_isBookmarked.value)
                _isBookmarked.value = !_isBookmarked.value
            }

            is Result.Error ->  _recipeBookmarkEvent.emit(false)
            else -> Unit
        }
    }
}