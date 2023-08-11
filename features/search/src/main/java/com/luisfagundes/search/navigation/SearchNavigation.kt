package com.luisfagundes.search.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.luisfagundes.feature.search.presentation.SearchRoute

const val searchNavigationRoute = "search/"

fun NavController.navigateToSearch(navOptions: NavOptions? = null) {
    navigate(searchNavigationRoute, navOptions)
}

fun NavGraphBuilder.searchScreen(
    onRecipeClick: (id: Int) -> Unit
) {
    composable(
        route = searchNavigationRoute,
    ) {
        SearchRoute(
            onRecipeClick = onRecipeClick
        )
    }
}
