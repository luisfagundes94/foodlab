package com.luisfagundes.remote.mappers

import com.luisfagundes.framework.extension.empty
import com.luisfagundes.remote.mappers.IngredientMapper.mapToDomain
import com.luisfagundes.remote.mappers.InstructionMapper.mapToDomain
import com.luisfagundes.recipe.data.models.RecipeResponse
import com.luisfagundes.recipe.domain.models.Recipe

object RecipeMapper {
    fun RecipeResponse.toDomainModel() =
        Recipe(
            id = this.id,
            title = this.title,
            image = this.image,
            servings = this.servings,
            readyInMinutes = this.readyInMinutes,
            sourceUrl = this.sourceUrl,
            aggregateLikes = this.aggregateLikes,
            spoonacularScore = this.spoonacularScore,
            healthScore = this.healthScore,
            cheap = this.cheap,
            vegetarian = this.vegetarian,
            vegan = this.vegan,
            dishTypes = this.dishTypes ?: emptyList(),
            summary = this.summary ?: String.empty(),
            sourceName = this.sourceName ?: String.empty(),
            ingredients = this.extendedIngredients?.mapToDomain(),
            dairyFree = this.dairyFree,
            glutenFree = this.glutenFree,
            veryHealthy = this.veryHealthy,
            veryPopular = this.veryPopular,
            sustainable = this.sustainable,
            instructions = this.analyzedInstructions?.mapToDomain() ?: emptyList()
        )
}
