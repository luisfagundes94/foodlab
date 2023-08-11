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
        iconTextId = 1,
        titleTextId = 1,
    ),
    SEARCH(
        selectedIcon = Icons.Default.Search,
        unselectedIcon = Icons.Outlined.Search,
        iconTextId = 1,
        titleTextId = 1,
    ),
    FAVORITES(
        selectedIcon = Icons.Default.Favorite,
        unselectedIcon = Icons.Outlined.Favorite,
        iconTextId = 1,
        titleTextId = 1,
    ),
    PANTRY(
        selectedIcon = Icons.Default.List,
        unselectedIcon = Icons.Outlined.List,
        iconTextId = 1,
        titleTextId = 1,
    )
}
