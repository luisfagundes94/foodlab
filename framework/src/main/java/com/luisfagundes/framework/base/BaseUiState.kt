package com.luisfagundes.framework.base

import com.luisfagundes.framework.network.Result

sealed interface BaseUiState<out R> {
    data object Loading : BaseUiState<Nothing>
    data class Success<T>(val data: T) : BaseUiState<T>
    data object Error : BaseUiState<Nothing>
}

fun <I, O> Result<I>.mapToUiState(onSuccess: (I) -> BaseUiState.Success<O>): BaseUiState<O> =
    when (this) {
        is Result.Success -> onSuccess(data)
        is Result.Error -> BaseUiState.Error
        is Result.Loading -> BaseUiState.Loading
    }
