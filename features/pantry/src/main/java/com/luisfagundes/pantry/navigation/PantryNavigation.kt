package com.luisfagundes.pantry.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.luisfagundes.pantry.presentation.PantryRoute

const val pantryNavigationGraph = "pantryGraph/"
const val pantryNavigationRoute = "pantry/"

fun NavController.navigateToPantry(navOptions: NavOptions? = null) {
    navigate(pantryNavigationRoute, navOptions)
}

fun NavGraphBuilder.pantryGraph(
    onAddIngredientClick: () -> Unit = {},
    nestedGraphs: NavGraphBuilder.() -> Unit
) {
    navigation(
        route = pantryNavigationGraph,
        startDestination = pantryNavigationRoute,
    ) {

        composable(
            route = pantryNavigationRoute,
        ) {
            PantryRoute(
                onAddIngredientClick = onAddIngredientClick
            )
        }
        nestedGraphs()
    }
}
