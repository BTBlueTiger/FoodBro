package com.bluetiger.foodbrocompose.database.room

import androidx.room.TypeConverter

interface DataConverter<T> {
    @TypeConverter
    fun fromData(data : T) : String
    @TypeConverter
    fun toData(json: String): T
}