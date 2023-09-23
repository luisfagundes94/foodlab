package com.luisfagundes.search.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.luisfagundes.domain.models.Recipe

@Composable
internal fun SearchResults(
    modifier: Modifier = Modifier,
    recipes: List<Recipe>,
) {
    LazyColumn(
        modifier = modifier,
    ) {
        items(recipes) { recipe ->
            Text(text = recipe.title)
        }
    }
}