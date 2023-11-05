package com.luisfagundes.foodlab.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Bookmarks
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
    SAVED(
        selectedIcon = Icons.Default.Bookmarks,
        unselectedIcon = Icons.Outlined.Bookmarks,
        iconTextId = com.luisfagundes.features.saved.R.string.saved,
        titleTextId = com.luisfagundes.features.saved.R.string.saved,
    ),
    PANTRY(
        selectedIcon = Icons.AutoMirrored.Filled.List,
        unselectedIcon = Icons.AutoMirrored.Outlined.List,
        iconTextId = com.luisfagundes.features.pantry.R.string.pantry,
        titleTextId = com.luisfagundes.features.pantry.R.string.pantry,
    )
}
