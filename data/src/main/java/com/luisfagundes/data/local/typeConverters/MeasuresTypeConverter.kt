package com.luisfagundes.data.local.typeConverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.luisfagundes.data.local.models.MeasuresEntity
import com.luisfagundes.domain.models.Measures

class MeasuresTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromMeasure(measures: MeasuresEntity?): String {
        return gson.toJson(measures)
    }

    @TypeConverter
    fun toMeasure(measureJson: String): MeasuresEntity {
        return gson.fromJson(measureJson, MeasuresEntity::class.java)
    }
}
