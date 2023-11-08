package com.luisfagundes.ingredients.presentation

import com.luisfagundes.domain.models.PantryCategory

interface AddIngredientUiState {
    data object Idle : AddIngredientUiState
    data object Loading : AddIngredientUiState
    data object Error : AddIngredientUiState
    data class Success(val categories: List<PantryCategory>) : AddIngredientUiState
}