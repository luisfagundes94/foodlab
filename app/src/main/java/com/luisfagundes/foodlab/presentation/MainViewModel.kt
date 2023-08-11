package com.luisfagundes.foodlab.presentation

import androidx.lifecycle.viewModelScope
import com.luisfagundes.domain.repositories.UserDataRepository
import com.luisfagundes.framework.base.mvvm.MvvmViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userDataRepository: UserDataRepository,
) : MvvmViewModel() {

    val uiState = userDataRepository.userData.map {
        MainUiState.Success(it)
    }.stateIn(
        scope = viewModelScope,
        initialValue = MainUiState.Loading,
        started = SharingStarted.WhileSubscribed(5_000),
    )
}
