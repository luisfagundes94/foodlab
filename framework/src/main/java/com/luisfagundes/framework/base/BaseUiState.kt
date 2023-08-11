package com.luisfagundes.framework.base

import com.luisfagundes.framework.network.DataState

sealed interface BaseUiState<out R> {
    data object Loading : BaseUiState<Nothing>
    data class Success<T>(val data: T) : BaseUiState<T>
    data class Error<T>(val throwable: Throwable) : BaseUiState<T>
}

fun <I, O> DataState<I>.mapToUiState(onSuccess: (I) -> BaseUiState.Success<O>): BaseUiState<O> =
    when (this) {
        is DataState.Success -> onSuccess(result)
        is DataState.Error -> BaseUiState.Error(error)
    }
