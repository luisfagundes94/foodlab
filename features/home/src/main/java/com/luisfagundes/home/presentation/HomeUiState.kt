package com.luisfagundes.home.presentation

import com.luisfagundes.domain.models.RecipeSections

sealed interface HomeUiState {
    data object Loading : HomeUiState

    data class Success(
        val recipeSections: RecipeSections
    ) : HomeUiState

    data object Error : HomeUiState
}