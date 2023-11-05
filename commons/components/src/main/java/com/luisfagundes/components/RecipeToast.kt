package com.luisfagundes.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.luisfagundes.commons.resources.R
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun DeletingRecipeToast(saveRecipeEvent: SharedFlow<Boolean>) {
    val context = LocalContext.current
    val successDeletingRecipeMsg = stringResource(R.string.recipe_deleted_successfully)
    val errorDeletingRecipeMsg = stringResource(R.string.recipe_deleted_error)

    LaunchedEffect(Unit) {
        saveRecipeEvent.collect { deleted ->
            showToast(
                context = context,
                message = if (deleted) successDeletingRecipeMsg else errorDeletingRecipeMsg,
            )
        }
    }
}

@Composable
fun SavingRecipeToast(saveRecipeEvent: SharedFlow<Boolean>) {
    val context = LocalContext.current
    val successSavingRecipeMsg = stringResource(R.string.recipe_saved_successfully)
    val errorSavingRecipeMsg = stringResource(R.string.error_saving_recipe)

    LaunchedEffect(Unit) {
        saveRecipeEvent.collect { deleted ->
            showToast(
                context = context,
                message = if (deleted) successSavingRecipeMsg else errorSavingRecipeMsg,
            )
        }
    }
}