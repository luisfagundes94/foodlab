package com.luisfagundes.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.luisfagundes.home.presentation.HomeRoute

const val homeNavigationGraph = "homeGraph/"
const val homeNavigationRoute = "home/"

fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    navigate(homeNavigationGraph, navOptions)
}

fun NavGraphBuilder.homeGraph(
    onRecipeClick: (id: String) -> Unit,
    nestedGraphs: NavGraphBuilder.() -> Unit,
) {
    navigation(
        route = homeNavigationGraph,
        startDestination = homeNavigationRoute,
    ) {
        composable(
            route = homeNavigationRoute
        ) {
            HomeRoute(
                onRecipeClick = onRecipeClick
            )
        }
        nestedGraphs()
    }
}
