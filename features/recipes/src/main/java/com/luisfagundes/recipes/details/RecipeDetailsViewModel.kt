package com.luisfagundes.recipes.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luisfagundes.domain.models.Recipe
import com.luisfagundes.domain.usecases.GetRecipeDetails
import com.luisfagundes.framework.decoder.StringDecoder
import com.luisfagundes.framework.network.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class RecipeDetailsViewModel @Inject constructor(
    getRecipeDetails: GetRecipeDetails,
    stringDecoder: StringDecoder,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val recipeDetailsArg = RecipeDetailsArg(savedStateHandle, stringDecoder)
    private val recipeId = recipeDetailsArg.recipeId

    val uiState: StateFlow<RecipeDetailsUiState> =
        getRecipeDetails(
            GetRecipeDetails.Params(recipeId.toInt())
        )
            .map { result -> handleResult(result) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Lazily,
                initialValue = RecipeDetailsUiState.Loading,
            )

    private fun handleResult(result: Result<Recipe>) =
        when (result) {
            is Result.Success -> RecipeDetailsUiState.Success(result.data)
            is Result.Error -> RecipeDetailsUiState.Error
            is Result.Loading -> RecipeDetailsUiState.Loading
        }
}