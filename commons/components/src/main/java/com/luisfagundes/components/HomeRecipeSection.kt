package com.luisfagundes.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkAdd
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.luisfagundes.common.components.R
import com.luisfagundes.domain.factory.FakeRecipeFactory
import com.luisfagundes.domain.models.Recipe
import com.luisfagundes.resources.theme.FoodlabTheme
import com.luisfagundes.resources.theme.ThemePreviews
import com.luisfagundes.resources.theme.spacing

@Composable
fun HomeRecipeSection(
    modifier: Modifier = Modifier,
    sectionTitle: String,
    recipes: List<Recipe>,
    onFavoriteClick: (id: Int) -> Unit,
    onRecipeClick: (id: Int) -> Unit,
) {
    Column(
        modifier = modifier,
    ) {
        Title(
            modifier = Modifier.padding(bottom = MaterialTheme.spacing.small),
            title = sectionTitle,
        )
        LazyRow {
            items(
                count = recipes.size,
                key = { index -> recipes[index].id }
            ) { index ->
                val recipe = recipes[index]
                Card(
                    modifier = Modifier
                        .width(160.dp)
                        .heightIn(min = 190.dp)
                        .clickable { onRecipeClick(recipe.id) },
                    title = recipe.title,
                    imageUrl = recipe.imageUrl,
                    onFavoriteClick = { onFavoriteClick(recipe.id) },
                )
            }
        }

    }
}

@Composable
private fun Card(
    modifier: Modifier = Modifier,
    title: String,
    imageUrl: String,
    onFavoriteClick: () -> Unit,
) {
    val isPreview = LocalInspectionMode.current

    ElevatedCard(
        modifier = modifier,
    ) {
        Box(
            contentAlignment = Alignment.TopEnd
        ) {
            if (isPreview) {
                Image(
                    painter = painterResource(id = R.drawable.recipe),
                    contentDescription = null
                )
            } else {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = title,
                    contentScale = ContentScale.FillBounds,
                )
            }
            Icon(
                modifier = Modifier
                    .padding(MaterialTheme.spacing.extraSmall)
                    .background(
                        color = MaterialTheme.colorScheme.surface.copy(alpha = .8f),
                        shape = CircleShape,
                    )
                    .padding(MaterialTheme.spacing.extraSmall)
                    .clickable { onFavoriteClick() },
                imageVector = Icons.Default.BookmarkAdd,
                contentDescription = null,
            )
        }

        Text(
            modifier = Modifier.padding(MaterialTheme.spacing.verySmall),
            text = title,
            style = MaterialTheme.typography.bodySmall,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
private fun Title(
    modifier: Modifier = Modifier,
    title: String,
) {
    Text(
        modifier = modifier,
        text = title,
        color = MaterialTheme.colorScheme.onSurface,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
    )
}

@ThemePreviews
@Composable
fun HomeRecipeSectionPreview() {
    FoodlabTheme {
        HomeRecipeSection(
            sectionTitle = "Popular",
            recipes = listOf(
                FakeRecipeFactory.recipe,
                FakeRecipeFactory.recipe,
            ),
            onFavoriteClick = {},
            onRecipeClick = {},
        )
    }
}