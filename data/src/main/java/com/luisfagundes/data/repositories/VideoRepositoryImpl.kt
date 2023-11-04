package com.luisfagundes.data.repositories

import com.luisfagundes.data.remote.service.RecipeService
import com.luisfagundes.data.remote.mappers.VideoGuideMapper.mapToDomain
import com.luisfagundes.domain.repositories.VideoRepository
import com.luisfagundes.framework.network.safeApiCall
import javax.inject.Inject

class VideoRepositoryImpl @Inject constructor(
    private val recipeService: RecipeService
) : VideoRepository {
    override suspend fun getVideoGuides() = safeApiCall {
        recipeService.fetchVideoGuides().videos.map { it.mapToDomain() }
    }
}