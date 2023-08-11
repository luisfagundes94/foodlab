package com.luisfagundes.framework.network

import com.luisfagundes.library.framework.network.handleThrowable

suspend fun <T : Any> safeApiCall(call: suspend () -> T): DataState<T> {
    return try {
        val response = call()
        DataState.Success(response)
    } catch (ex: Throwable) {
        DataState.Error(ex.handleThrowable())
    }
}
