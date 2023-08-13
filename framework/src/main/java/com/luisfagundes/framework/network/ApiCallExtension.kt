package com.luisfagundes.framework.network

import com.luisfagundes.library.framework.network.handleThrowable

suspend fun <T : Any> safeApiCall(call: suspend () -> T): Result<T> {
    return try {
        val response = call()
        Result.Success(response)
    } catch (ex: Throwable) {
        Result.Error(ex.handleThrowable())
    }
}
