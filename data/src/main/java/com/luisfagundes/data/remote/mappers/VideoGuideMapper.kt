package com.luisfagundes.data.remote.mappers

import com.luisfagundes.data.remote.models.VideoGuideResponse
import com.luisfagundes.domain.models.VideoGuide
import com.luisfagundes.framework.extension.empty

object VideoGuideMapper {
    fun VideoGuideResponse.mapToDomain() = VideoGuide(
        title = title,
        rating = rating,
        thumbnail = thumbnail ?: String.empty(),
        youtubeId = youTubeId ?: String.empty(),
    )
}