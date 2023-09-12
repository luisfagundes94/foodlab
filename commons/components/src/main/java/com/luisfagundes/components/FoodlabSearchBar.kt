package com.luisfagundes.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.luisfagundes.common.components.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodlabSearchBar(
    query: String = "",
    searching: Boolean = false,
    placeholderHint: String,
    onQueryChange: (String) -> Unit = {},
    onClear: () -> Unit = {},
    onSearch: (String) -> Unit = {}
) {
    DockedSearchBar(
        query = query,
        onQueryChange = onQueryChange,
        placeholder = { Text(placeholderHint) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(R.string.search)
            )
        },
        trailingIcon = {
            if (searching) {
                IconButton(
                    onClick = onClear
                ) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = stringResource(R.string.clear_search)
                    )
                }
            }
        },
        onSearch = onSearch,
        active = false,
        onActiveChange = { }
    ) {

    }
}