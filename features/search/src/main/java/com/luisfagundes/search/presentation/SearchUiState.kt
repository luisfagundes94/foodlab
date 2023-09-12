package com.luisfagundes.search.presentation

import com.luisfagundes.domain.models.Recipe
import com.luisfagundes.domain.models.VideoGuide

data class SearchUiState(
    val recipes: List<Recipe> = emptyList(),
    val videoGuides: List<VideoGuide> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
)