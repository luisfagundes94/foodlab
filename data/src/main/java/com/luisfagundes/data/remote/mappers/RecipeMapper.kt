package com.luisfagundes.data.remote.mappers

import com.luisfagundes.data.local.models.RecipeEntity
import com.luisfagundes.data.remote.models.RecipeResponse
import com.luisfagundes.domain.models.Recipe
import com.luisfagundes.framework.extension.empty
import com.luisfagundes.data.remote.mappers.IngredientMapper.mapToDomain
import com.luisfagundes.data.remote.mappers.IngredientMapper.toDomainModel
import com.luisfagundes.data.remote.mappers.IngredientMapper.toEntityModel
import com.luisfagundes.data.remote.mappers.InstructionMapper.mapToDomain
import com.luisfagundes.data.remote.mappers.InstructionMapper.toDomainModel
import com.luisfagundes.data.remote.mappers.InstructionMapper.toEntityModel

object RecipeMapper {
    fun RecipeResponse.toDomainModel() =
        Recipe(
            id = this.id,
            title = this.title,
            imageUrl = this.image,
            serves = this.servings,
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
            instructions = this.analyzedInstructions?.mapToDomain() ?: emptyList(),
            bookmarked = false,
        )

    fun RecipeEntity.toDomainModel(): Recipe {
        return Recipe(
            id = this.id,
            title = this.title,
            imageUrl = this.imageUrl,
            serves = this.serves,
            readyInMinutes = this.readyInMinutes,
            sourceUrl = this.sourceUrl,
            aggregateLikes = this.aggregateLikes,
            spoonacularScore = this.spoonacularScore,
            healthScore = this.healthScore,
            cheap = this.cheap,
            ingredients = this.ingredients?.map { it.toDomainModel() },
            vegetarian = this.vegetarian,
            vegan = this.vegan,
            dishTypes = this.dishTypes.orEmpty(),
            summary = this.summary,
            sourceName = this.sourceName,
            glutenFree = this.glutenFree,
            dairyFree = this.dairyFree,
            veryHealthy = this.veryHealthy,
            veryPopular = this.veryPopular,
            sustainable = this.sustainable,
            instructions = this.instructions?.map { it.toDomainModel() } ?: emptyList(),
            bookmarked = true
        )
    }

    fun Recipe.toEntityModel(): RecipeEntity {
        return RecipeEntity(
            id = this.id,
            title = this.title,
            imageUrl = this.imageUrl,
            serves = this.serves,
            readyInMinutes = this.readyInMinutes,
            sourceUrl = this.sourceUrl,
            aggregateLikes = this.aggregateLikes,
            spoonacularScore = this.spoonacularScore,
            healthScore = this.healthScore,
            cheap = this.cheap,
            ingredients = this.ingredients?.map { it.toEntityModel()} ?: emptyList(),
            vegetarian = this.vegetarian,
            vegan = this.vegan,
            dishTypes = this.dishTypes,
            summary = this.summary,
            sourceName = this.sourceName,
            glutenFree = this.glutenFree,
            dairyFree = this.dairyFree,
            veryHealthy = this.veryHealthy,
            veryPopular = this.veryPopular,
            sustainable = this.sustainable,
            instructions = this.instructions?.map { it.toEntityModel() } ?: emptyList(),
            bookmarked = true,
        )
    }
}
