package com.bluetiger.foodbrocompose.feature_open_food_facts.data.local.data_source

import androidx.room.Dao
import androidx.room.Query
import com.bluetiger.foodbrocompose.database.room.BaseDao
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model.OpenFoodFactsData
import com.bluetiger.foodbrocompose.feature_user.domain.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface OpenFoodFactsDao : BaseDao<OpenFoodFactsData> {

    @Query("SELECT * FROM OpenFoodFactsResponses WHERE timeStamp = :timeStamp")
    suspend fun getOpenFoodFactsByTimeStamp(timeStamp: Long) : OpenFoodFactsData

    @Query("SELECT * FROM OpenFoodFactsResponses WHERE userMail =:userMail")
    fun getOpenFoodFactResponsesByUser(userMail: String) : Flow<List<OpenFoodFactsData>>

    @Query("SELECT * FROM OpenFoodFactsResponses")
    fun getAllFoodFactsResponses(): Flow<List<OpenFoodFactsData>>

}