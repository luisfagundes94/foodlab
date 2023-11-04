package com.luisfagundes.data.local.typeConverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.luisfagundes.data.local.models.InstructionEntity

class InstructionTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromInstructionEntityList(value: List<InstructionEntity>?): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toInstructionEntityList(value: String?): List<InstructionEntity>? {
        if (value == null) {
            return null
        }
        val objectType = object : TypeToken<List<InstructionEntity>>() {}.type
        return gson.fromJson(value, objectType)
    }
}

