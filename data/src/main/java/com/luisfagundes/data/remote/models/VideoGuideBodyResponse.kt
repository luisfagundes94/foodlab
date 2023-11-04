package com.luisfagundes.data.remote.models

data class VideoGuideBodyResponse(
    val totalResults: Int,
    val videos: List<VideoGuideResponse>
)
