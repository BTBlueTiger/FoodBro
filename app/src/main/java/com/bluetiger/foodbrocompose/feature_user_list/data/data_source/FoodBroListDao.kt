package com.bluetiger.foodbrocompose.feature_user_list.data.data_source

import androidx.room.Dao
import androidx.room.Query
import com.bluetiger.foodbrocompose.database.room.BaseDao
import com.bluetiger.foodbrocompose.feature_user_list.domain.model.FoodBroList
import kotlinx.coroutines.flow.Flow


@Dao
interface FoodBroListDao : BaseDao<FoodBroList> {

    @Query("SELECT * FROM food_bro_list WHERE userName = :name")
    fun getFoodBroListByUser(name: String) : Flow<List<FoodBroList>>
}