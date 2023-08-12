package com.luisfagundes.saved.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.luisfagundes.saved.presentation.SavedRoute

const val savedNavigationRoute = "saved/"

fun NavController.navigateToSaved(navOptions: NavOptions? = null) {
    navigate(savedNavigationRoute, navOptions)
}

fun NavGraphBuilder.savedScreen(
    onRecipeClick: (id: Int) -> Unit
) {
    composable(
        route = savedNavigationRoute,
    ) {
        SavedRoute(
            onRecipeClick = onRecipeClick
        )
    }
}
