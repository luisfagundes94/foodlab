package com.luisfagundes.recipes.list.presentation

import com.luisfagundes.domain.models.Recipe

sealed interface RecipeListUiState {
    data object Idle : RecipeListUiState
    data object Loading : RecipeListUiState
    data object Error : RecipeListUiState
    data class Success(val recipes: List<Recipe>) : RecipeListUiState
}