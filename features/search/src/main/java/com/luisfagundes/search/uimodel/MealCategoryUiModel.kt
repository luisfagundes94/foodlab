package com.luisfagundes.search.uimodel

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class MealCategoryUiModel(
    @StringRes val titleResId: Int,
    @DrawableRes val imageResId: Int
)
