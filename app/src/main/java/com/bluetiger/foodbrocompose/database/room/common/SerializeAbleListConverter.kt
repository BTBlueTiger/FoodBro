package com.bluetiger.foodbrocompose.database.room.common

import androidx.room.TypeConverter
import com.bluetiger.foodbrocompose.database.room.DataConverter
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class SerializeAbleListConverter  {
    @TypeConverter
    fun fromLongData(data: SerializeAbleList<Long>): String = Json.encodeToString(data)

    @TypeConverter
    fun fromStringData(data: SerializeAbleList<String>): String = Json.encodeToString(data)

    @TypeConverter
    fun toStringData(json: String): SerializeAbleList<String>  = Json.decodeFromString(json)

    @TypeConverter
    fun toLongData(json: String) : SerializeAbleList<Long> = Json.decodeFromString(json)

}