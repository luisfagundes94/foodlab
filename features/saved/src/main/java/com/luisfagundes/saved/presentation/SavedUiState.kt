package com.luisfagundes.saved.presentation

import com.luisfagundes.domain.models.Recipe

sealed interface SavedUiState {
    data object Idle : SavedUiState
    data object Loading : SavedUiState
    data object Empty : SavedUiState
    data class Success(val recipes: List<Recipe>) : SavedUiState
}