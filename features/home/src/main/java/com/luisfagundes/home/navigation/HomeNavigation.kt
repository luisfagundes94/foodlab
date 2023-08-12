package com.luisfagundes.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.luisfagundes.home.presentation.HomeRoute

const val homeNavigationRoute = "home/"

fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    navigate(homeNavigationRoute, navOptions)
}

fun NavGraphBuilder.homeScreen(
    onRecipeClick: (id: Int) -> Unit
) {
    composable(
        route = homeNavigationRoute
    ) {
        HomeRoute(
            onRecipeClick = onRecipeClick
        )
    }
}
