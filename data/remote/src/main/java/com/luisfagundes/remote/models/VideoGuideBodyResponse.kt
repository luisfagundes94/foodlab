package com.luisfagundes.remote.models

data class VideoGuideBodyResponse(
    val totalResults: Int,
    val videos: List<VideoGuideResponse>
)
