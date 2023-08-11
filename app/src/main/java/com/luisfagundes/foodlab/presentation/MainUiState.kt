package com.luisfagundes.foodlab.presentation

import com.luisfagundes.domain.models.UserData

sealed interface MainUiState {
    data object Loading : MainUiState
    data class Success(val userData: UserData) : MainUiState
}
