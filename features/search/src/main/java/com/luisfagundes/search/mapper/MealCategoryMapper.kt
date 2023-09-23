package com.luisfagundes.search.mapper

import com.luisfagundes.domain.models.MealCategory
import com.luisfagundes.features.search.R
import com.luisfagundes.search.uiModel.MealCategoryUiModel

object MealCategoryMapper {
    fun MealCategory.mapToUiModel(): MealCategoryUiModel {
        return when (this) {
            MealCategory.LUNCH -> MealCategoryUiModel(R.string.lunch, R.drawable.lunch)
            MealCategory.BREAKFAST -> MealCategoryUiModel(R.string.breakfast, R.drawable.breakfast)
            MealCategory.DESSERT -> MealCategoryUiModel(R.string.dessert, R.drawable.dessert)
            MealCategory.MAIN_DISH -> MealCategoryUiModel(R.string.main_dish, R.drawable.main_dish)
            MealCategory.SNACK -> MealCategoryUiModel(R.string.snack, R.drawable.snack)
            MealCategory.SIDE_DISH -> MealCategoryUiModel(R.string.side_dish, R.drawable.side_dish)
            MealCategory.SALAD -> MealCategoryUiModel(R.string.salad, R.drawable.salad)
            MealCategory.APPETIZER -> MealCategoryUiModel(R.string.appetizer, R.drawable.appetizer)
        }
    }
}
