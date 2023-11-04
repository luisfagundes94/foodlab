package com.luisfagundes.data.local.typeConverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.luisfagundes.data.local.models.StepEntity

class StepTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromStepList(steps: List<StepEntity>?): String? {
        return gson.toJson(steps)
    }

    @TypeConverter
    fun toStepList(stepsString: String?): List<StepEntity>? {
        if (stepsString == null) {
            return null
        }
        val type = object : TypeToken<List<StepEntity>>() {}.type
        return gson.fromJson(stepsString, type)
    }
}
