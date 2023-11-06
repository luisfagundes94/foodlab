package com.luisfagundes.search.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import com.luisfagundes.components.LoadingView
import com.luisfagundes.domain.enums.MealType
import com.luisfagundes.features.search.R
import com.luisfagundes.resources.theme.spacing
import com.luisfagundes.search.presentation.VideoGuideUiState

private const val YOUTUBE_BASE_URL = "https://www.youtube.com/watch?v="

@Composable
fun StaticContent(
    modifier: Modifier = Modifier,
    videoGuideUiState: VideoGuideUiState,
    onMealTypeClick: (MealType) -> Unit,
) {
    val mealCategories = MealType.entries
    val uriHandler = LocalUriHandler.current

    Column(
        modifier = modifier,
    ) {
        MealCategoryGrid(
            mealTypes = mealCategories,
            onClick = onMealTypeClick
        )
        when (videoGuideUiState) {
            is VideoGuideUiState.Loading -> LoadingView(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = MaterialTheme.spacing.default)
            )

            is VideoGuideUiState.Error -> Text(
                modifier = Modifier.padding(vertical = MaterialTheme.spacing.default),
                text = stringResource(R.string.error)
            )

            is VideoGuideUiState.Success -> VideoGuides(
                title = stringResource(R.string.video_guides),
                videoGuideList = videoGuideUiState.videoGuideList,
                onVideoClick = {
                    if (it.isNotEmpty()) {
                        uriHandler.openUri("$YOUTUBE_BASE_URL$it")
                    }
                }
            )
        }
    }
}
