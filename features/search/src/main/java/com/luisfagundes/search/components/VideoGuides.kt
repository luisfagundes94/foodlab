package com.luisfagundes.search.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.luisfagundes.domain.models.VideoGuide
import com.luisfagundes.features.search.R
import com.luisfagundes.resources.theme.spacing

@Composable
internal fun VideoGuides(
    title: String,
    videoGuideList: List<VideoGuide>,
    onVideoClick: (youtubeKey: String) -> Unit = {},
) {
    if (videoGuideList.isEmpty()) return

    Text(
        modifier = Modifier.padding(vertical = MaterialTheme.spacing.default),
        text = title,
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.titleLarge
    )
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(
            space = MaterialTheme.spacing.verySmall,
            alignment = Alignment.Start,
        )
    ) {
        items(videoGuideList.size) { index ->
            VideoGuide(
                videoGuide = videoGuideList[index],
                onVideoClick = onVideoClick
            )
        }
    }
}

@Composable
private fun VideoGuide(
    videoGuide: VideoGuide,
    onVideoClick: (youtubeKey: String) -> Unit = {}
) {
    ElevatedCard(
        modifier = Modifier
            .width(150.dp)
            .clickable { onVideoClick(videoGuide.youtubeId) }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomStart
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxWidth(),
                model = videoGuide.thumbnail,
                contentDescription = videoGuide.title,
                contentScale = ContentScale.Crop,
            )
            IconButton(
                modifier = Modifier
                    .padding(MaterialTheme.spacing.verySmall)
                    .background(
                        color = Color.Black.copy(alpha = 0.5f),
                        shape = CircleShape
                    ),
                onClick = { onVideoClick(videoGuide.youtubeId) },
            ) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    tint = Color.White,
                    contentDescription = stringResource(R.string.play_video)
                )
            }
        }
        Text(
            modifier = Modifier.padding(MaterialTheme.spacing.verySmall),
            text = videoGuide.title,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}