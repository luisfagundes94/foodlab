package com.luisfagundes.search.presentation

import com.luisfagundes.domain.models.VideoGuide

sealed interface VideoGuideUiState {
    data class Success(
        val videoGuideList: List<VideoGuide>,
    ) : VideoGuideUiState

    data object Error : VideoGuideUiState
    data object Loading : VideoGuideUiState
}