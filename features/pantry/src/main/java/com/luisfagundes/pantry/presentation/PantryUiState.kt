package com.luisfagundes.pantry.presentation

import com.luisfagundes.domain.models.PantryCategory

interface PantryUiState {
    data object Idle : PantryUiState
    data object Loading : PantryUiState
    data object Error : PantryUiState
    data class Success(val categories: List<PantryCategory>) : PantryUiState
}