package com.luisfagundes.home.presentation

import androidx.lifecycle.viewModelScope
import com.luisfagundes.domain.models.RecipeSections
import com.luisfagundes.domain.usecases.GetRecipeSections
import com.luisfagundes.framework.base.mvvm.MvvmViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import com.luisfagundes.framework.network.Result
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getRecipeSections: GetRecipeSections,
) : MvvmViewModel() {

    val uiState: StateFlow<HomeUiState> =
        getRecipeSections()
            .map { result -> handleResult(result) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Lazily,
                initialValue = HomeUiState.Loading
            )

    private fun handleResult(result: Result<RecipeSections>) = when (result) {
        is Result.Success -> HomeUiState.Success(result.data)
        is Result.Error -> HomeUiState.Error
        is Result.Loading -> HomeUiState.Loading
    }

    fun saveRecipe(id: Int) {

    }
}
