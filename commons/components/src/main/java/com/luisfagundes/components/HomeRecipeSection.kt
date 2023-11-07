package com.luisfagundes.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.luisfagundes.domain.factory.FakeRecipeFactory
import com.luisfagundes.domain.models.Recipe
import com.luisfagundes.resources.theme.FoodlabTheme
import com.luisfagundes.resources.theme.FoodlabThemePreview
import com.luisfagundes.resources.theme.spacing

@Composable
fun HomeRecipeSection(
    modifier: Modifier = Modifier,
    sectionTitle: String,
    recipes: List<Recipe>,
    onRecipeClick: (id: String) -> Unit,
) {
    Column(
        modifier = modifier,
    ) {
        Title(
            modifier = Modifier.padding(
                vertical = MaterialTheme.spacing.verySmall,
                horizontal = MaterialTheme.spacing.default
            ),
            title = sectionTitle,
        )
        LazyRow(
            contentPadding = PaddingValues(
                horizontal = MaterialTheme.spacing.default
            ),
            horizontalArrangement = Arrangement.spacedBy(
                space = MaterialTheme.spacing.small,
                alignment = Alignment.Start,
            ),
        ) {
            items(
                count = recipes.size,
                key = { index -> recipes[index].id }
            ) { index ->
                val recipe = recipes[index]
                RecipeItem(
                    modifier = Modifier
                        .width(160.dp)
                        .heightIn(min = 170.dp)
                        .clickable { onRecipeClick(recipe.id.toString()) },
                    title = recipe.title,
                    imageUrl = recipe.imageUrl,
                    showActionIcon = false,
                )
            }
        }
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

@FoodlabThemePreview
@Composable
fun HomeRecipeSectionPreview() {
    FoodlabTheme {
        HomeRecipeSection(
            sectionTitle = "Popular",
            recipes = listOf(
                FakeRecipeFactory.recipe,
                FakeRecipeFactory.recipe,
            ),
            onRecipeClick = {},
        )
    }
}