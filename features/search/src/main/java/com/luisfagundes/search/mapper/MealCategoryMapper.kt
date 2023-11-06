package com.luisfagundes.search.mapper

import com.luisfagundes.domain.enums.MealType
import com.luisfagundes.features.search.R
import com.luisfagundes.search.uiModel.MealCategoryUiModel

object MealCategoryMapper {
    fun MealType.mapToUiModel(): MealCategoryUiModel {
        return when (this) {
            MealType.LUNCH -> MealCategoryUiModel(R.string.lunch, R.drawable.lunch)
            MealType.BREAKFAST -> MealCategoryUiModel(R.string.breakfast, R.drawable.breakfast)
            MealType.DESSERT -> MealCategoryUiModel(R.string.dessert, R.drawable.dessert)
            MealType.MAIN_COURSE -> MealCategoryUiModel(R.string.main_course, R.drawable.main_dish)
            MealType.SNACK -> MealCategoryUiModel(R.string.snack, R.drawable.snack)
            MealType.SIDE_DISH -> MealCategoryUiModel(R.string.side_dish, R.drawable.side_dish)
            MealType.SALAD -> MealCategoryUiModel(R.string.salad, R.drawable.salad)
            MealType.APPETIZER -> MealCategoryUiModel(R.string.appetizer, R.drawable.appetizer)
        }
    }
}
