package com.luisfagundes.framework.network

import timber.log.Timber

suspend fun <T : Any> safeApiCall(call: suspend () -> T): Result<T> {
    return try {
        val response = call()
        Result.Success(response)
    } catch (exception: Exception) {
        Timber.tag("Result.Error").d(exception)
        Result.Error(exception)
    }
}
