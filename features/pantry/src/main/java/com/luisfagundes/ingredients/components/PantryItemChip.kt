package com.luisfagundes.ingredients.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import com.luisfagundes.domain.models.PantryItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PantryItemChip(
    modifier: Modifier = Modifier,
    item: PantryItem
) {
    var selected by rememberSaveable { mutableStateOf(false) }

    FilterChip(
        modifier = modifier,
        selected = selected,
        onClick = { selected = !selected },
        label = { Text(item.name.capitalize(Locale.current)) }
    )
}