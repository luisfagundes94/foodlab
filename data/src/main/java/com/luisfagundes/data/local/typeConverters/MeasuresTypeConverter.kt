package com.luisfagundes.data.local.typeConverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.luisfagundes.domain.models.Measures

class MeasuresTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromMeasure(measures: Measures?): String {
        return gson.toJson(measures)
    }

    @TypeConverter
    fun toMeasure(measureJson: String): Measures {
        return gson.fromJson(measureJson, Measures::class.java)
    }
}
