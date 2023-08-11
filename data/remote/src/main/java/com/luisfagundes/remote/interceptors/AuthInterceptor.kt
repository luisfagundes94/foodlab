package com.luisfagundes.remote.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val apiKey: String
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestUrl = request.url
        val newUrl = requestUrl.newBuilder()
            .addQueryParameter(API_KEY, apiKey)
            .addQueryParameter(LIMIT_LICENSE, true.toString())
            .build()

        return chain.proceed(
            request.newBuilder()
                .url(newUrl)
                .build()
        )
    }

    private companion object {
        const val API_KEY = "apiKey"
        const val LIMIT_LICENSE = "limitLicense"
    }
}
