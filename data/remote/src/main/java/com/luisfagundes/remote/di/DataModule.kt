package com.luisfagundes.remote.di

import com.luisfagundes.domain.repositories.RecipeRepository
import com.luisfagundes.domain.repositories.UserDataRepository
import com.luisfagundes.domain.repositories.VideoRepository
import com.luisfagundes.remote.repositories.RecipeRepositoryImpl
import com.luisfagundes.remote.repositories.VideoRepositoryImpl
import com.luisfagundes.remote.utils.ConnectivityManager
import com.luisfagundes.remote.utils.NetworkMonitor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsRecipeRepository(
        recipeRepository: RecipeRepositoryImpl,
    ): RecipeRepository

    @Binds
    fun bindsVideoRepository(
        videoRepository: VideoRepositoryImpl,
    ): VideoRepository

    @Binds
    fun bindsConnectivityManager(
        connectivityManager: ConnectivityManager,
    ): NetworkMonitor
}
