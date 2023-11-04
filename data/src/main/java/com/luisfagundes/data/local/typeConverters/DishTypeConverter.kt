package com.luisfagundes.data.local.typeConverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DishTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromDishTypeList(dishTypes: List<String>?): String {
        return gson.toJson(dishTypes)
    }

    @TypeConverter
    fun toDishTypeList(dishTypeString: String?): List<String>? {
        if (dishTypeString == null) return emptyList()
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(dishTypeString, type)
    }
}
