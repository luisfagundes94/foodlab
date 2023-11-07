package com.luisfagundes.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkAdd
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.AsyncImage
import com.luisfagundes.commons.components.R
import com.luisfagundes.resources.theme.spacing
import com.luisfagundes.commons.resources.R.drawable.placeholder as placeholder_dark
import com.luisfagundes.commons.resources.R.drawable.placeholder_white as placeholder_light

@Composable
fun RecipeItem(
    modifier: Modifier = Modifier,
    title: String,
    titleStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    showActionIcon: Boolean = true,
    showDeleteIcon: Boolean = false,
    imageUrl: String,
    onIconClick: () -> Unit = {},
) {
    val isPreview = LocalInspectionMode.current

    ElevatedCard(
        modifier = modifier,
    ) {
        Box(
            contentAlignment = Alignment.TopEnd
        ) {
            val actionIcon = if (showDeleteIcon) Icons.Default.Delete
            else Icons.Default.BookmarkAdd

            if (isPreview) {
                Image(
                    painter = painterResource(id = R.drawable.recipe),
                    contentDescription = null
                )
            } else {
                AsyncImage(
                    modifier = Modifier.fillMaxWidth(),
                    model = imageUrl,
                    contentDescription = title,
                    contentScale = ContentScale.Crop,
                )
            }
            if (showActionIcon) {
                Icon(
                    modifier = Modifier
                        .padding(MaterialTheme.spacing.extraSmall)
                        .background(
                            color = MaterialTheme.colorScheme.surface.copy(alpha = .8f),
                            shape = CircleShape,
                        )
                        .padding(MaterialTheme.spacing.extraSmall)
                        .clickable { onIconClick() },
                    imageVector = actionIcon,
                    contentDescription = null,
                )
            }
        }
        Text(
            modifier = Modifier.padding(MaterialTheme.spacing.verySmall),
            text = title,
            style = titleStyle,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )
    }
}