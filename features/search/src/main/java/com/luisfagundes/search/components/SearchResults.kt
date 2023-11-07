package com.luisfagundes.search.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
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
    onRecipeClick: (id: String) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(
            space = MaterialTheme.spacing.small,
            alignment = Alignment.CenterVertically,
        )
    ) {
        items(recipes) { recipe ->
            RecipeItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 170.dp)
                    .clickable { onRecipeClick(recipe.id.toString()) },
                title = recipe.title,
                titleStyle = MaterialTheme.typography.titleLarge,
                imageUrl = recipe.imageUrl,
                showActionIcon = false,
            )
        }
    }
}