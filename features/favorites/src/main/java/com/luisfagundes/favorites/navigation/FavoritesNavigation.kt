package com.luisfagundes.feature.favorites.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.luisfagundes.feature.favorites.presentation.FavoritesRoute

const val favoritesNavigationRoute = "favorites/"

fun NavController.navigateToFavorites(navOptions: NavOptions? = null) {
    navigate(favoritesNavigationRoute, navOptions)
}

fun NavGraphBuilder.favoritesScreen(
    onRecipeClick: (id: Int) -> Unit
) {
    composable(
        route = favoritesNavigationRoute,
    ) {
        FavoritesRoute(
            onRecipeClick = onRecipeClick
        )
    }
}
