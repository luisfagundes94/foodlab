package com.luisfagundes.recipes.details.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luisfagundes.domain.models.Recipe
import com.luisfagundes.domain.usecases.GetRecipeDetails
import com.luisfagundes.framework.decoder.StringDecoder
import com.luisfagundes.framework.network.Result
import com.luisfagundes.recipes.details.navigation.RecipeDetailsArg
import com.luisfagundes.recipes.details.presentation.RecipeDetailsUiState.Error
import com.luisfagundes.recipes.details.presentation.RecipeDetailsUiState.Idle
import com.luisfagundes.recipes.details.presentation.RecipeDetailsUiState.Loading
import com.luisfagundes.recipes.details.presentation.RecipeDetailsUiState.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailsViewModel @Inject constructor(
    private val getRecipeDetails: GetRecipeDetails,
    stringDecoder: StringDecoder,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val recipeDetailsArg = RecipeDetailsArg(savedStateHandle, stringDecoder)
    private val recipeId = recipeDetailsArg.recipeId

    private val _uiState = MutableStateFlow<RecipeDetailsUiState>(Idle)
    val uiState = _uiState.asStateFlow()

    fun refreshRecipeDetails() = viewModelScope.launch {
        val params = GetRecipeDetails.Params(recipeId.toInt())
        getRecipeDetails(params).collect { result ->
            _uiState.value = handleResult(result)
        }
    }

    private fun handleResult(result: Result<Recipe>) = when (result) {
        is Result.Success -> Success(result.data)
        is Result.Error -> Error
        is Result.Loading -> Loading
    }
}