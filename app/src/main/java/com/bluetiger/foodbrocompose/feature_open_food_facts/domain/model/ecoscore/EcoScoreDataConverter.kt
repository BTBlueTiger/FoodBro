package com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model.ecoscore

import androidx.room.TypeConverter
import com.bluetiger.foodbrocompose.database.room.DataConverter
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class EcoScoreDataConverter : DataConverter<EcoScoreData> {
    @TypeConverter
    override fun fromData(data: EcoScoreData): String = Json.encodeToString(data)

    @TypeConverter
    override fun toData(json: String): EcoScoreData = Json.decodeFromString(json)
}