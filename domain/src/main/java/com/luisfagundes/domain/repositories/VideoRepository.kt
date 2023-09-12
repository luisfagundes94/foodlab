package com.luisfagundes.domain.repositories

import com.luisfagundes.domain.models.VideoGuide
import com.luisfagundes.framework.network.Result

interface VideoRepository {
    suspend fun getVideoGuides(): Result<List<VideoGuide>>
}