package com.luisfagundes.data.local.typeConverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.luisfagundes.data.local.models.MeasureEntity
import com.luisfagundes.domain.models.Measure

class MeasureTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromMeasure(measure: MeasureEntity?): String {
        return gson.toJson(measure)
    }

    @TypeConverter
    fun toMeasure(measureJson: String): MeasureEntity {
        return gson.fromJson(measureJson, MeasureEntity::class.java)
    }
}
