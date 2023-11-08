package com.luisfagundes.ingredients.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.luisfagundes.ingredients.presentation.AddIngredientRoute

const val addIngredientNavigationRoute = "addIngredient/"

fun NavController.navigateToAddIngredient(navOptions: NavOptions? = null) {
    navigate(addIngredientNavigationRoute, navOptions)
}

fun NavGraphBuilder.addIngredientScreen(
    onBackClick: () -> Unit = {},
) {
    composable(
        route = addIngredientNavigationRoute,
    ) {
        AddIngredientRoute(
            onBackClick = onBackClick
        )
    }
}