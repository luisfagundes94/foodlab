package com.luisfagundes.data.remote.mappers

import com.luisfagundes.data.local.models.IngredientEntity
import com.luisfagundes.data.local.models.MeasureEntity
import com.luisfagundes.data.local.models.MeasuresEntity
import com.luisfagundes.data.remote.models.IngredientResponse
import com.luisfagundes.data.remote.models.MeasureResponse
import com.luisfagundes.data.remote.models.MeasuresResponse
import com.luisfagundes.domain.models.Ingredient
import com.luisfagundes.domain.models.Measure
import com.luisfagundes.domain.models.Measures
import com.luisfagundes.framework.extension.empty
import java.util.UUID

private const val BASE_IMAGE_URL = "https://spoonacular.com/cdn/ingredients_100x100/"

object IngredientMapper {
    fun List<IngredientResponse>.mapToDomain(): List<Ingredient> {
        return this.map { it.toDomain() }
    }

    fun IngredientEntity.toDomainModel(): Ingredient {
        return Ingredient(
            id = this.id,
            name = this.name,
            amount = this.amount,
            imageUrl = this.imageUrl,
            measures = this.measures.mapToDomain(),
            originalMeasure = this.originalMeasure
        )
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

    fun Ingredient.toEntityModel(): IngredientEntity {
        return IngredientEntity(
            id = this.id,
            name = this.name,
            amount = this.amount,
            imageUrl = this.imageUrl,
            measures = this.measures.mapToEntity(),
            originalMeasure = this.originalMeasure
        )
    }

    private fun Measures.mapToEntity(): MeasuresEntity {
        return MeasuresEntity(
            id = this.id,
            metric = this.metric.mapToEntity(),
            us = this.us.mapToEntity()
        )
    }

    private fun Measure.mapToEntity(): MeasureEntity {
        return MeasureEntity(
            id = this.id,
            amount = this.amount,
            unitLong = this.unitLong,
            unitShort = this.unitShort
        )
    }

    private fun MeasuresResponse.mapToDomain(): Measures {
        return Measures(
            id = UUID.randomUUID().hashCode(),
            metric = this.metric.toDomain(),
            us = this.us.toDomain()
        )
    }

    private fun MeasuresEntity.mapToDomain(): Measures {
        return Measures(
            id = this.id,
            metric = this.metric.toDomain(),
            us = this.us.toDomain()
        )
    }

    private fun MeasureResponse.toDomain(): Measure {
        return Measure(
            id = UUID.randomUUID().hashCode(),
            amount = this.amount,
            unitLong = this.unitLong,
            unitShort = this.unitShort
        )
    }

    private fun MeasureEntity.toDomain(): Measure {
        return Measure(
            id = this.id,
            amount = this.amount,
            unitLong = this.unitLong,
            unitShort = this.unitShort
        )
    }
}
