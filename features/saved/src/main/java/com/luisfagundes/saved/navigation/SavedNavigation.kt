package com.luisfagundes.saved.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.luisfagundes.saved.presentation.SavedRoute

const val savedNavigationGraph = "savedGraph/"
const val savedNavigationRoute = "saved/"

fun NavController.navigateToSaved(navOptions: NavOptions? = null) {
    navigate(savedNavigationGraph, navOptions)
}

fun NavGraphBuilder.savedGraph(
    onRecipeClick: (id: String) -> Unit,
    nestedGraphs: NavGraphBuilder.() -> Unit = {},
) {
    navigation(
        route = savedNavigationGraph,
        startDestination = savedNavigationRoute,
    ) {
        composable(
            route = savedNavigationRoute,
        ) {
            SavedRoute(
                onRecipeClick = onRecipeClick
            )
        }
        nestedGraphs()
    }
}
