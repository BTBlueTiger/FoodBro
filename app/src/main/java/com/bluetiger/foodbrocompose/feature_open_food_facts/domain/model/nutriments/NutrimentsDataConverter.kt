package com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model.nutriments

import androidx.room.TypeConverter
import com.bluetiger.foodbrocompose.database.room.DataConverter
import kotlinx.serialization.json.Json
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString

class NutrimentsDataConverter : DataConverter<Nutriments> {
    @TypeConverter
    override fun fromData(data: Nutriments): String = Json.encodeToString(data)
    @TypeConverter
    override fun toData(json: String): Nutriments = Json.decodeFromString(json)
}