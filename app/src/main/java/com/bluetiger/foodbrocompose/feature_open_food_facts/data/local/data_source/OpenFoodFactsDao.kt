package com.bluetiger.foodbrocompose.feature_open_food_facts.data.local.data_source

import androidx.room.Dao
import androidx.room.Query
import com.bluetiger.foodbrocompose.database.room.BaseDao
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model.OpenFoodFactsData
import kotlinx.coroutines.flow.Flow

@Dao
interface OpenFoodFactsDao : BaseDao<OpenFoodFactsData> {

    @Query("SELECT * FROM OpenFoodFactsResponses WHERE timeStamp = :timeStamp")
    suspend fun getOpenFoodFactsByTimeStamp(timeStamp: Long) : OpenFoodFactsData
    @Query("SELECT * FROM OpenFoodFactsResponses WHERE userName =:userName")
    fun getOpenFoodFactResponsesByUser(userName: String) : Flow<List<OpenFoodFactsData>>
}