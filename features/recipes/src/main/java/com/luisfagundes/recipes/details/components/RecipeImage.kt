package com.luisfagundes.recipes.details.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.luisfagundes.common.components.R
import com.luisfagundes.domain.models.Recipe

private const val BOTTOM_CORNER_PERCENT = 5

@Composable
fun RecipeImage(isPreview: Boolean, recipe: Recipe) {
    if (isPreview) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .clip(
                    shape = RoundedCornerShape(
                        bottomEndPercent = BOTTOM_CORNER_PERCENT,
                        bottomStartPercent = BOTTOM_CORNER_PERCENT,
                    )
                ),
            painter = painterResource(id = R.drawable.recipe),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
    } else {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .clip(
                    shape = RoundedCornerShape(
                        bottomEndPercent = BOTTOM_CORNER_PERCENT,
                        bottomStartPercent = BOTTOM_CORNER_PERCENT,
                    )
                ),
            model = recipe.imageUrl,
            contentDescription = recipe.title,
            contentScale = ContentScale.Crop,
        )
    }
}