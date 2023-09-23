package com.luisfagundes.search.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.luisfagundes.components.RecipeItem
import com.luisfagundes.domain.models.Recipe
import com.luisfagundes.resources.theme.spacing

@Composable
internal fun SearchResults(
    modifier: Modifier = Modifier,
    recipes: List<Recipe>,
    onRecipeClick: (id: Int) -> Unit,
    onFavoriteClick: (id: Int) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(
            space = MaterialTheme.spacing.verySmall,
            alignment = Alignment.CenterVertically,
        )
    ) {
        items(recipes) { recipe ->
            RecipeItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 170.dp)
                    .clickable { onRecipeClick(recipe.id) },
                title = recipe.title,
                titleStyle = MaterialTheme.typography.titleLarge,
                imageUrl = recipe.imageUrl,
                onFavoriteClick = { onFavoriteClick(recipe.id) },
            )
        }
    }
}