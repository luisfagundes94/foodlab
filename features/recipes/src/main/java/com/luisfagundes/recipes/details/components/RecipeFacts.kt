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
internal fun RecipeFacts(
    modifier: Modifier = Modifier,
    recipe: Recipe,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        Item(
            title = stringResource(R.string.min),
            value = recipe.readyInMinutes.toString(),
        )
        Item(
            title = stringResource(R.string.serves),
            value = "${recipe.serves}",
        )
        Item(
            title = stringResource(id = R.string.health),
            value = "${recipe.healthScore}%",
        )
        Item(
            title = stringResource(R.string.likes),
            value = recipe.aggregateLikes.toString(),
        )
    }
}

@Composable
private fun Item(
    title: String,
    value: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = value,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = title,
        )
    }
}