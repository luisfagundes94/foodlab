package com.luisfagundes.recipes.list.navigation

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.luisfagundes.framework.decoder.StringDecoder
import com.luisfagundes.recipes.list.presentation.RecipeListRoute
import org.jetbrains.annotations.VisibleForTesting

@VisibleForTesting
internal const val mealTypeArg = "mealType"
internal const val recipeListRoute = "recipeList/"

internal class RecipeListArg(val mealType: String) {
    constructor(
        savedStateHandle: SavedStateHandle,
        stringDecoder: StringDecoder
    ) : this(stringDecoder.decode(checkNotNull(savedStateHandle[mealTypeArg])))
}

fun NavController.navigateToRecipeList(mealType: String) {
    val encodedId = Uri.encode(mealType)
    this.navigate("$recipeListRoute$encodedId") {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.recipeListScreen(
    onBackClick: () -> Unit,
    onRecipeClick: (id: String) -> Unit,
) {
    composable(
        route = "$recipeListRoute{$mealTypeArg}",
        arguments = listOf(
            navArgument(mealTypeArg) { type = NavType.StringType },
        ),
    ) {
        RecipeListRoute(
            onBackClick = onBackClick,
            onRecipeClick = onRecipeClick
        )
    }
}