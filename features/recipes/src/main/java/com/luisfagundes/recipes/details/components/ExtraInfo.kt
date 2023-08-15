package com.luisfagundes.recipes.details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.luisfagundes.domain.models.Recipe
import com.luisfagundes.features.recipes.R

@Composable
internal fun ExtraInfo(
    modifier: Modifier = Modifier,
    recipe: Recipe,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        Item(
            title = stringResource(R.string.spoonacular_score),
            count = "${recipe.spoonacularScore}%",
        )
        Item(
            title = stringResource(R.string.likes),
            count = recipe.aggregateLikes.toString(),
        )
        Item(
            title = stringResource(R.string.min),
            count = recipe.readyInMinutes.toString(),
        )
        Item(
            title = stringResource(id = R.string.health),
            count = "${recipe.healthScore}%",
        )
    }
}

@Composable
private fun Item(
    title: String,
    count: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = count,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = title,
        )
    }
}