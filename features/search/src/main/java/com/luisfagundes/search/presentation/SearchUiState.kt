package com.luisfagundes.search.presentation

import com.luisfagundes.domain.models.Recipe

sealed interface SearchUiState {
    data object Loading : SearchUiState
    data class Success(val recipes: List<Recipe>) : SearchUiState
    data object Error : SearchUiState
}