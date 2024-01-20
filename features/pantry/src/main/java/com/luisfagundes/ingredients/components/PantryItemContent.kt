package com.luisfagundes.ingredients.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.luisfagundes.components.FilterChipItem
import com.luisfagundes.domain.models.PantryCategory
import com.luisfagundes.domain.models.PantryItem
import com.luisfagundes.resources.theme.spacing

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
internal fun AddIngredientContent(
    modifier: Modifier = Modifier,
    categories: List<PantryCategory>,
    selectedIngredients: List<PantryItem>,
    onSelectedIngredient: (ingredient: PantryItem) -> Unit = {},
    onDeselectedIngredient: (ingredient: PantryItem) -> Unit = {},
) {
    val categoryNames = categories.map { it.name }
    var selectedCategoryName by rememberSaveable { mutableStateOf(categoryNames.first()) }

    Column(
        modifier = modifier,
    ) {
        LazyRow(
            modifier = Modifier.padding(vertical = MaterialTheme.spacing.verySmall)
        ) {
            items(categoryNames) { name ->
                TextButton(
                    modifier = Modifier.background(
                        color = if (name == selectedCategoryName) {
                            MaterialTheme.colorScheme.onPrimary
                        } else {
                            MaterialTheme.colorScheme.surface
                        },
                        shape = MaterialTheme.shapes.extraLarge,
                    ),
                    onClick = { selectedCategoryName = name },
                ) {
                    Text(
                        text = name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        }
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
            categories.filter { it.name == selectedCategoryName }
                .flatMap { it.items }
                .forEach { item ->
                    FilterChipItem(
                        modifier = Modifier.height(FilterChipDefaults.Height.plus(MaterialTheme.spacing.extraSmall)),
                        item = item,
                        selected = selectedIngredients.contains(item),
                        onSelected = onSelectedIngredient,
                        onDeselected = onDeselectedIngredient,
                    )
                }
        }
    }
}