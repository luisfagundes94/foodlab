package com.luisfagundes.search.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.luisfagundes.search.presentation.SearchRoute

const val searchNavigationRoute = "search/"

fun NavController.navigateToSearch(navOptions: NavOptions? = null) {
    navigate(searchNavigationRoute, navOptions)
}

fun NavGraphBuilder.searchGraph(
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
