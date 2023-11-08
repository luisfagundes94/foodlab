package com.luisfagundes.data.remote.di

import com.luisfagundes.data.local.repository.LocalPantryRepository
import com.luisfagundes.data.remote.utils.ConnectivityManager
import com.luisfagundes.data.remote.utils.NetworkMonitor
import com.luisfagundes.data.repositories.RecipeRepositoryImpl
import com.luisfagundes.data.repositories.VideoRepositoryImpl
import com.luisfagundes.domain.repositories.PantryRepository
import com.luisfagundes.domain.repositories.RecipeRepository
import com.luisfagundes.domain.repositories.VideoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.bytebuddy.asm.Advice.Local

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
    fun bindsPantryRepository(
        pantryRepository: LocalPantryRepository,
    ): PantryRepository

    @Binds
    fun bindsConnectivityManager(
        connectivityManager: ConnectivityManager,
    ): NetworkMonitor
}
