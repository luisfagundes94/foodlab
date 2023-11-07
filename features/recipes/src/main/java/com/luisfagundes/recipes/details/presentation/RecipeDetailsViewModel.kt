package com.luisfagundes.recipes.details.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.luisfagundes.domain.models.Recipe
import com.luisfagundes.domain.repositories.RecipeRepository
import com.luisfagundes.domain.usecases.GetRecipeDetails
import com.luisfagundes.framework.base.mvvm.BaseViewModel
import com.luisfagundes.framework.decoder.StringDecoder
import com.luisfagundes.framework.network.Result
import com.luisfagundes.recipes.details.navigation.RecipeDetailsArg
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RecipeDetailsViewModel @Inject constructor(
    private val getRecipeDetails: GetRecipeDetails,
    private val repository: RecipeRepository,
    stringDecoder: StringDecoder,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel() {

    private val recipeDetailsArg = RecipeDetailsArg(savedStateHandle, stringDecoder)
    private val recipeId = recipeDetailsArg.recipeId

    private val _uiState = MutableStateFlow<RecipeDetailsUiState>(RecipeDetailsUiState.Idle)
    val uiState = _uiState.asStateFlow()

    private val _saveRecipeEvent = MutableSharedFlow<Boolean>()
    val saveRecipeEvent = _saveRecipeEvent.asSharedFlow()

    private val _deleteRecipeEvent = MutableSharedFlow<Boolean>()
    val deleteRecipeEvent = _deleteRecipeEvent.asSharedFlow()

    private val _isBookmarked = MutableStateFlow(false)
    val isBookmarked = _isBookmarked.asStateFlow()

    fun refreshRecipeDetails() = viewModelScope.launch {
        val params = GetRecipeDetails.Params(recipeId.toInt())
        getRecipeDetails(params).collect { result ->
            _uiState.update { setState(result) }
        }
    }

    fun handleRecipeEvent(shouldBookmark: Boolean, recipe: Recipe?) {
        if (shouldBookmark) deleteRecipe(recipe)
        else saveRecipe(recipe)
    }

    private fun saveRecipe(recipe: Recipe?) = safeLaunch {
        recipe?.let {
            when (repository.saveRecipe(recipe)) {
                is Result.Success -> {
                    _saveRecipeEvent.emit(true)
                    _isBookmarked.value = true
                }
                is Result.Error -> _saveRecipeEvent.emit(false)
                else -> Unit
            }
        } ?: Timber.e(NULL_RECIPE_MESSAGE)
    }

    private fun deleteRecipe(recipe: Recipe?) = safeLaunch {
        recipe?.let {
            when (repository.deleteRecipe(recipe)) {
                is Result.Success -> {
                    _deleteRecipeEvent.emit(true)
                    _isBookmarked.value = false
                }
                is Result.Error -> _deleteRecipeEvent.emit(false)
                else -> Unit
            }
        } ?: Timber.e(NULL_RECIPE_MESSAGE)
    }

    private fun setState(result: Result<Recipe>) = when (result) {
        is Result.Success -> {
            _isBookmarked.value = result.data.bookmarked
            RecipeDetailsUiState.Success(result.data)
        }
        is Result.Error -> RecipeDetailsUiState.Error
        is Result.Loading -> RecipeDetailsUiState.Loading
    }

    private companion object {
        const val NULL_RECIPE_MESSAGE = "recipe is null"
    }
}