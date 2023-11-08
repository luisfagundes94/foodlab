package com.luisfagundes.pantry.presentation

import com.luisfagundes.domain.repositories.PantryRepository
import com.luisfagundes.framework.base.mvvm.BaseViewModel
import com.luisfagundes.framework.network.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PantryViewModel @Inject constructor(
    private val repository: PantryRepository,
) : BaseViewModel() {

    private val _uiState = MutableStateFlow<PantryUiState>(PantryUiState.Idle)
    val uiState: StateFlow<PantryUiState> = _uiState.asStateFlow()

    fun fetchPantryCategories() = safeLaunch {
        _uiState.value = PantryUiState.Loading

        val result = repository.fetchPantryCategories()

        _uiState.value = when (result) {
            is Result.Success -> PantryUiState.Success(result.data)
            is Result.Error -> PantryUiState.Error
            is Result.Loading -> PantryUiState.Loading
        }
    }
}
