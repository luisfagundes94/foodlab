package com.luisfagundes.data.remote.di

import android.content.Context
import com.luisfagundes.data.BuildConfig
import com.luisfagundes.data.remote.datasources.MockedRecipeDataSource
import com.luisfagundes.data.remote.datasources.RemoteRecipeDataSource
import com.luisfagundes.data.remote.interceptors.AuthInterceptor
import com.luisfagundes.data.remote.provider.createApiService
import com.luisfagundes.data.remote.provider.createRetrofit
import com.luisfagundes.data.remote.service.RecipeService
import com.luisfagundes.domain.datasources.RecipeDataSource
import com.luisfagundes.framework.network.createOkHttpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideApiService(
        retrofit: Retrofit
    ) = createApiService(retrofit)

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory
    ): Retrofit = createRetrofit(okHttpClient, converterFactory)

    @Provides
    @Singleton
    fun provideAuthInterceptor() = AuthInterceptor(apiKey = BuildConfig.API_KEY)

    @Provides
    @Singleton
    fun provideOkHttpClient(
        authInterceptor: AuthInterceptor
    ) = createOkHttpClient(
        isDev = BuildConfig.DEBUG,
        interceptors = listOf(
            authInterceptor
        )
    )

    @Provides
    @Singleton
    fun provideGsonConverter(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideRemoteRecipeDataSource(
        apiService: RecipeService
    ): RecipeDataSource = RemoteRecipeDataSource(apiService)

//    @Provides
//    @Singleton
//    fun provideMockedRecipeDataSource(
//        @ApplicationContext appContext: Context
//    ): RecipeDataSource = MockedRecipeDataSource(appContext)
}
