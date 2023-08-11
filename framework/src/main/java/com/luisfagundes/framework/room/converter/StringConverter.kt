package com.luisfagundes.library.framework.room.converter

import androidx.room.TypeConverter
import com.luisfagundes.library.framework.extension.fromJson
import com.luisfagundes.library.framework.extension.toJson

class StringConverter {
    @TypeConverter
    fun toListOfStrings(stringValue: String): List<String>? {
        return stringValue.fromJson()
    }

    @TypeConverter
    fun fromListOfStrings(listOfString: List<String>?): String {
        return listOfString.toJson()
    }
}
