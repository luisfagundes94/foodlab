package com.luisfagundes.feature.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.luisfagundes.feature.home.presentation.HomeRoute

const val homeNavigationRoute = "home/"
const val recipeId = "recipeId"

fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    navigate(homeNavigationRoute, navOptions)
}

fun NavGraphBuilder.homeScreen(
    onRecipeClick: (id: Int) -> Unit
) {
    composable(
        route = homeNavigationRoute,
        arguments = listOf(
            navArgument(recipeId) { type = NavType.IntType },
        ),
    ) {
        HomeRoute(
            onRecipeClick = onRecipeClick
        )
    }
}
