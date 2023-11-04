package com.luisfagundes.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class RecipeEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "image_url") val imageUrl: String,
    @ColumnInfo(name = "serves") val serves: Int,
    @ColumnInfo(name = "ready_in_minutes") val readyInMinutes: Int,
    @ColumnInfo(name = "source_url") val sourceUrl: String?,
    @ColumnInfo(name = "aggregate_likes") val aggregateLikes: Int,
    @ColumnInfo(name = "spoonacular_score") val spoonacularScore: Int,
    @ColumnInfo(name = "health_score") val healthScore: Int,
    @ColumnInfo(name = "cheap") val cheap: Boolean,
    @ColumnInfo(name = "ingredients") val ingredients: List<IngredientEntity>?,
    @ColumnInfo(name = "vegetarian") val vegetarian: Boolean,
    @ColumnInfo(name = "vegan") val vegan: Boolean,
    @ColumnInfo(name = "dish_types") val dishTypes: List<String>?,
    @ColumnInfo(name = "summary") val summary: String,
    @ColumnInfo(name = "source_name") val sourceName: String,
    @ColumnInfo(name = "gluten_free") val glutenFree: Boolean?,
    @ColumnInfo(name = "dairy_free") val dairyFree: Boolean?,
    @ColumnInfo(name = "very_healthy") val veryHealthy: Boolean?,
    @ColumnInfo(name = "very_popular") val veryPopular: Boolean?,
    @ColumnInfo(name = "sustainable") val sustainable: Boolean?,
    @ColumnInfo(name = "instructions") val instructions: List<InstructionEntity>?
)
