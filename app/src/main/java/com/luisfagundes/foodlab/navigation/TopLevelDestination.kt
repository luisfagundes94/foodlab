package com.luisfagundes.foodlab.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector

enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: Int,
    val titleTextId: Int,
) {
    HOME(
        selectedIcon = Icons.Default.Home,
        unselectedIcon = Icons.Outlined.Home,
        iconTextId = com.luisfagundes.foodlab.features.home.R.string.home,
        titleTextId = com.luisfagundes.foodlab.features.home.R.string.home,
    ),
    SEARCH(
        selectedIcon = Icons.Default.Search,
        unselectedIcon = Icons.Outlined.Search,
        iconTextId = com.luisfagundes.features.search.R.string.search,
        titleTextId = com.luisfagundes.features.search.R.string.search,
    ),
    FAVORITES(
        selectedIcon = Icons.Default.Favorite,
        unselectedIcon = Icons.Outlined.Favorite,
        iconTextId = com.luisfagundes.features.favorites.R.string.favorites,
        titleTextId = com.luisfagundes.features.favorites.R.string.favorites,
    ),
    PANTRY(
        selectedIcon = Icons.Default.List,
        unselectedIcon = Icons.Outlined.List,
        iconTextId = com.luisfagundes.features.pantry.R.string.pantry,
        titleTextId = com.luisfagundes.features.pantry.R.string.pantry,
    )
}
