package com.luisfagundes.remote.models

data class VideoGuideResponse(
    val title: String,
    val length: Int,
    val rating: Float,
    val shortTitle: String,
    val thumbnail: String?,
    val views: Int?,
    val youTubeId: String?
)
