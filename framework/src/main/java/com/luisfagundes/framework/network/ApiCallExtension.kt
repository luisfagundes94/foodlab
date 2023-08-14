package com.luisfagundes.framework.network

import com.luisfagundes.library.framework.network.handleThrowable
import timber.log.Timber

suspend fun <T : Any> safeApiCall(call: suspend () -> T): Result<T> {
    return try {
        val response = call()
        Result.Success(response)
    } catch (ex: Throwable) {
        Timber.tag("Result.Error").d(ex)
        Result.Error(ex.handleThrowable())
    }
}
