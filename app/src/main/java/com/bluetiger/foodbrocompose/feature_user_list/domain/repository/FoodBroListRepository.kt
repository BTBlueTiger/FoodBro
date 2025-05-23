package com.bluetiger.foodbrocompose.feature_user_list.domain.repository

import com.bluetiger.foodbrocompose.feature_user.domain.model.User
import com.bluetiger.foodbrocompose.feature_user_list.domain.model.FoodBroList
import kotlinx.coroutines.flow.Flow

abstract class FoodBroListRepository {

    abstract fun getFoodBroListsByUser(user: User) : Flow<List<FoodBroList>>
    abstract suspend fun insertFoodBroList(foodBroList: FoodBroList)
    abstract suspend fun deleteFoodBroList(foodBroList: FoodBroList)
}