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
    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState = _uiState.asStateFlow()

    fun fetchVideoGuides() = safeLaunch {
        _uiState.update { it.copy(isLoading = true) }
        val result = getVideoGuides.invoke()
        handleVideoResult(result)
    }

    private fun handleVideoResult(result: Result<List<VideoGuide>>) {
        _uiState.update {
            when (result) {
                is Result.Loading -> it.copy(isLoading = true, isError = false)
                is Result.Success -> it.copy(videoGuides = result.data)
                is Result.Error -> it.copy(isError = true, isLoading = false)
            }
        }
    }
}