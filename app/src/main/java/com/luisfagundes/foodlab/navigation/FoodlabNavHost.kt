package com.luisfagundes.foodlab.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.luisfagundes.saved.navigation.savedGraph
import com.luisfagundes.foodlab.ui.FoodlabAppState
import com.luisfagundes.home.navigation.homeGraph
import com.luisfagundes.pantry.navigation.pantryGraph
import com.luisfagundes.home.navigation.homeNavigationGraph
import com.luisfagundes.ingredients.navigation.addIngredientScreen
import com.luisfagundes.ingredients.navigation.navigateToAddIngredient
import com.luisfagundes.recipes.details.navigation.navigateToRecipeDetails
import com.luisfagundes.recipes.details.navigation.recipeDetailsScreen
import com.luisfagundes.recipes.list.navigation.navigateToRecipeList
import com.luisfagundes.recipes.list.navigation.recipeListScreen
import com.luisfagundes.search.navigation.searchGraph

@Composable
fun FoodlabNavHost(
    appState: FoodlabAppState,
    modifier: Modifier = Modifier,
    startDestination: String = homeNavigationGraph,
) {
    val navController = appState.navController

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        homeGraph(
            onRecipeClick = navController::navigateToRecipeDetails,
            nestedGraphs = {
                recipeDetailsScreen(
                    onBackClick = navController::popBackStack,
                )
            }
        )
        searchGraph(
            onRecipeClick = navController::navigateToRecipeDetails,
            onMealTypeClick = {
                navController.navigateToRecipeList(it.type)
            }
        )
        savedGraph(
            onRecipeClick = navController::navigateToRecipeDetails,
            nestedGraphs = {
                recipeDetailsScreen(
                    onBackClick = navController::popBackStack,
                )
            }
        )
        pantryGraph(
            onAddIngredientClick = navController::navigateToAddIngredient,
            nestedGraphs = {
                addIngredientScreen(
                    onBackClick = navController::popBackStack,
                )
            }
        )
        recipeListScreen(
            onBackClick = navController::popBackStack,
            onRecipeClick = navController::navigateToRecipeDetails,
        )
    }
}
