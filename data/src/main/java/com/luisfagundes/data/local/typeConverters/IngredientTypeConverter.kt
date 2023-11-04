package com.luisfagundes.data.local.typeConverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.luisfagundes.data.local.models.IngredientEntity

class IngredientTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromIngredientEntityList(ingredients: List<IngredientEntity>?): String? {
        if (ingredients == null) return null
        val type = object : TypeToken<List<IngredientEntity>>() {}.type
        return gson.toJson(ingredients, type)
    }

    @TypeConverter
    fun toIngredientEntityList(ingredientEntityString: String?): List<IngredientEntity>? {
        if (ingredientEntityString == null) return null
        val type = object : TypeToken<List<IngredientEntity>>() {}.type
        return gson.fromJson(ingredientEntityString, type)
    }
}
