package com.luisfagundes.remote.repositories

import com.luisfagundes.domain.repositories.VideoRepository
import com.luisfagundes.framework.network.safeApiCall
import com.luisfagundes.remote.mappers.VideoGuideMapper.mapToDomain
import com.luisfagundes.remote.service.RecipeService
import javax.inject.Inject

class VideoRepositoryImpl @Inject constructor(
    private val recipeService: RecipeService
) : VideoRepository {
    override suspend fun getVideoGuides() = safeApiCall {
        recipeService.fetchVideoGuides().videos.map { it.mapToDomain() }
    }
}