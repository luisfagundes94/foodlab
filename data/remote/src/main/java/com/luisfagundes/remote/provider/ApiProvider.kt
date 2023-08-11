package com.luisfagundes.remote.provider

import com.luisfagundes.data.remote.BuildConfig
import com.luisfagundes.framework.network.createOkHttpClient
import com.luisfagundes.remote.service.RecipeService
import com.luisfagundes.remote.interceptors.AuthInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


fun createApiService(retrofit: Retrofit): RecipeService =
    retrofit.create(RecipeService::class.java)

fun createRetrofit(
    okHttpClient: OkHttpClient,
    converterFactory: GsonConverterFactory
): Retrofit = Retrofit.Builder()
    .baseUrl(BuildConfig.BASE_URL)
    .client(okHttpClient)
    .addConverterFactory(converterFactory)
    .build()

fun createAuthInterceptor() = AuthInterceptor(apiKey = BuildConfig.API_KEY)
