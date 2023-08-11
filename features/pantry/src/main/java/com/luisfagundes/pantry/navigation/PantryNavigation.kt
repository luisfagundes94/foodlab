package com.luisfagundes.feature.pantry.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.luisfagundes.feature.pantry.presentation.PantryRoute

const val pantryNavigationRoute = "pantry/"

fun NavController.navigateToPantry(navOptions: NavOptions? = null) {
    navigate(pantryNavigationRoute, navOptions)
}

fun NavGraphBuilder.pantryScreen() {
    composable(
        route = pantryNavigationRoute,
    ) {
        PantryRoute()
    }
}
