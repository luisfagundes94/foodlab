package com.luisfagundes.data.local.typeConverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.luisfagundes.domain.models.Measure

class MeasureTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromMeasure(measure: Measure?): String {
        return gson.toJson(measure)
    }

    @TypeConverter
    fun toMeasure(measureJson: String): Measure {
        return gson.fromJson(measureJson, Measure::class.java)
    }
}
