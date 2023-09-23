package com.luisfagundes.search.presentation

import com.luisfagundes.domain.models.Recipe
import com.luisfagundes.domain.models.VideoGuide

sealed interface SearchUiState {

    data class Success(val recipes: List<Recipe>) : SearchUiState
    data object Searching : SearchUiState

    data object Loading : SearchUiState

    data object NotSearching : SearchUiState

    data object Error : SearchUiState

    data object Empty : SearchUiState
}