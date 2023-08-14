package com.luisfagundes.recipes.details

import com.luisfagundes.domain.models.Recipe

sealed interface RecipeDetailsUiState {
    data object Loading : RecipeDetailsUiState
    data class Success(val recipe: Recipe) : RecipeDetailsUiState
    data object Error : RecipeDetailsUiState
}