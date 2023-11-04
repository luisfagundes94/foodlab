package com.luisfagundes.data.remote.provider

import com.luisfagundes.data.BuildConfig
import com.luisfagundes.data.remote.interceptors.AuthInterceptor
import com.luisfagundes.data.remote.service.RecipeService
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
