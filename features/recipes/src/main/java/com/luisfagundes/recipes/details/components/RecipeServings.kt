package com.luisfagundes.recipes.details.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.luisfagundes.features.recipes.R
import com.luisfagundes.resources.theme.spacing

@Composable
internal fun RecipeServings(
    servings: Int,
    onRemoveClick: () -> Unit = {},
    onAddClick: () -> Unit = {},
) {
    Row(
        modifier = Modifier.offset(x = -(MaterialTheme.spacing.small)),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(
            onClick = { onRemoveClick() }
        ) {
            Icon(
                imageVector = Icons.Default.Remove,
                contentDescription = null,
            )
        }
        Text(
            text = "${stringResource(id = R.string.serves)} $servings",
        )
        IconButton(
            onClick = { onAddClick() }
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
            )
        }
    }
}