package com.luisfagundes.framework.base

import com.luisfagundes.framework.network.Result

sealed interface BaseUiState<out R> {
    data object Loading : BaseUiState<Nothing>
    data class Success<T>(val data: T) : BaseUiState<T>
    data class Error<T>(val throwable: Throwable) : BaseUiState<T>
}

fun <I, O> Result<I>.mapToUiState(onSuccess: (I) -> BaseUiState.Success<O>): BaseUiState<O> =
    when (this) {
        is com.luisfagundes.framework.network.DataState.Result.Success -> onSuccess(data)
        is com.luisfagundes.framework.network.DataState.Result.Error -> BaseUiState.Error(error)
    }
