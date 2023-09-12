package com.luisfagundes.remote.mappers

import com.luisfagundes.domain.models.VideoGuide
import com.luisfagundes.framework.extension.empty
import com.luisfagundes.remote.models.VideoGuideResponse

object VideoGuideMapper {
    fun VideoGuideResponse.mapToDomain() = VideoGuide(
        title = title,
        rating = rating,
        thumbnail = thumbnail ?: String.empty(),
        youtubeId = youTubeId ?: String.empty(),
    )
}