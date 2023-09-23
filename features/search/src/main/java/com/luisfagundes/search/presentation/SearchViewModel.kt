package com.luisfagundes.search.presentation

import com.luisfagundes.domain.models.VideoGuide
import com.luisfagundes.domain.usecases.GetVideoGuides
import com.luisfagundes.framework.base.mvvm.BaseViewModel
import com.luisfagundes.framework.network.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getVideoGuides: GetVideoGuides
) : BaseViewModel() {

    private val _searchUiState = MutableStateFlow<SearchUiState>(SearchUiState.NotSearching)
    val searchUiState = _searchUiState.asStateFlow()

    private val _videoGuideUiState = MutableStateFlow<VideoGuideUiState>(VideoGuideUiState.Loading)
    val videoGuideUiState = _videoGuideUiState.asStateFlow()

    fun fetchVideoGuides() = safeLaunch {
        _videoGuideUiState.update { VideoGuideUiState.Loading }
        val result = getVideoGuides.invoke()
        handleVideoResult(result)
    }

    private fun handleVideoResult(result: Result<List<VideoGuide>>) {
        _videoGuideUiState.update {
            when (result) {
                is Result.Loading -> VideoGuideUiState.Loading
                is Result.Success -> VideoGuideUiState.Success(result.data)
                is Result.Error -> VideoGuideUiState.Error
            }
        }
    }
}