package com.luisfagundes.pantry.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.luisfagundes.domain.models.PantryCategory
import com.luisfagundes.domain.models.PantryItem
import com.luisfagundes.resources.theme.FoodlabTheme
import com.luisfagundes.resources.theme.FoodlabThemePreview
import com.luisfagundes.resources.theme.spacing

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PantryCategory(
    modifier: Modifier = Modifier,
    category: PantryCategory,
    onDeleteItem: (item: PantryItem) -> Unit = {},
) {
    var expanded by remember { mutableStateOf(true) }
    val categoryText = pantryCategoryTitle(category)

    Column(
        modifier = modifier,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = !expanded }
        ) {
            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                text = categoryText,
                style = MaterialTheme.typography.titleLarge
            )
            Icon(
                modifier = Modifier.padding(horizontal = MaterialTheme.spacing.verySmall),
                imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                contentDescription = if (expanded) "Collapse" else "Expand"
            )
        }

        AnimatedVisibility(expanded) {
            Box(
                modifier = Modifier
                    .heightIn(
                        min = 50.dp,
                        max = 2000.dp
                    )
                    .weight(1f)
            ) {
                FlowRow(
                    modifier = Modifier.padding(vertical = MaterialTheme.spacing.verySmall),
                    horizontalArrangement = Arrangement.spacedBy(
                        space = MaterialTheme.spacing.extraSmall,
                        alignment = Alignment.Start
                    ),
                    verticalArrangement = Arrangement.spacedBy(
                        space = MaterialTheme.spacing.extraSmall,
                        alignment = Alignment.Top
                    ),
                ) {
                    category.items.forEach { item ->
                        PantryItem(
                            modifier = Modifier.height(AssistChipDefaults.Height),
                            item = item,
                            onDeleteItem = onDeleteItem
                        )
                    }
                }
            }
        }
    }
}


@Composable
private fun PantryItem(
    modifier: Modifier = Modifier,
    item: PantryItem,
    onDeleteItem: (item: PantryItem) -> Unit = {},
) {
    AssistChip(
        modifier = modifier,
        onClick = { onDeleteItem(item) },
        label = { Text(item.name.capitalize(Locale.current)) },
    )
}

@Composable
@FoodlabThemePreview
private fun PantryCategoryPreview() {
    FoodlabTheme {
        Surface {

        }
    }
}
