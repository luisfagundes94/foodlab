package com.luisfagundes.remote.mappers

import com.luisfagundes.data.responses.MeasureResponse
import com.luisfagundes.data.responses.MeasuresResponse
import com.luisfagundes.domain.models.Ingredient
import com.luisfagundes.domain.models.Measure
import com.luisfagundes.domain.models.Measures
import com.luisfagundes.framework.extension.empty
import com.luisfagundes.recipe.data.models.IngredientResponse

private const val BASE_IMAGE_URL = "https://spoonacular.com/cdn/ingredients_100x100/"

object IngredientMapper {
    fun List<IngredientResponse>.mapToDomain(): List<Ingredient> {
        return this.map { it.toDomain() }
    }

    private fun IngredientResponse.toDomain(): Ingredient {
        return Ingredient(
            id = this.id,
            amount = this.amount,
            name = this.name,
            imageUrl = BASE_IMAGE_URL + this.image,
            measures = this.measures.mapToDomain(),
            originalMeasure = this.original ?: String.empty()
        )
    }

    private fun MeasuresResponse.mapToDomain(): Measures {
        return Measures(
            metric = this.metric.toDomain(),
            us = this.us.toDomain()
        )
    }

    private fun MeasureResponse.toDomain(): Measure {
        return Measure(
            amount = this.amount,
            unitLong = this.unitLong,
            unitShort = this.unitShort
        )
    }
}
