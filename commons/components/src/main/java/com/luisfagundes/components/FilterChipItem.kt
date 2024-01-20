package com.luisfagundes.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import com.luisfagundes.domain.models.PantryItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterChipItem(
    modifier: Modifier = Modifier,
    item: PantryItem,
    selected: Boolean = false,
    onSelected: (item: PantryItem) -> Unit = {},
    onDeselected: (item: PantryItem) -> Unit = {}
) {

    FilterChip(
        modifier = modifier,
        selected = selected,
        onClick = {
            if (selected) onSelected(item) else onDeselected(item)
        },
        label = { Text(item.name.capitalize(Locale.current)) }
    )
}