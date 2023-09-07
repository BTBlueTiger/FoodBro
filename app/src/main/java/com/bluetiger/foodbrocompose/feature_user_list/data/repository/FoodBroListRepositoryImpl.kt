package com.bluetiger.foodbrocompose.feature_user_list.data.repository

import com.bluetiger.foodbrocompose.feature_user.domain.model.User
import com.bluetiger.foodbrocompose.feature_user_list.data.data_source.FoodBroListDao
import com.bluetiger.foodbrocompose.feature_user_list.domain.model.FoodBroList
import com.bluetiger.foodbrocompose.feature_user_list.domain.repository.FoodBroListRepository
import kotlinx.coroutines.flow.Flow

class FoodBroListRepositoryImpl(private val dao: FoodBroListDao) : FoodBroListRepository() {
    override fun getFoodBroListsByUser(user: User): Flow<List<FoodBroList>> {
        return dao.getFoodBroListByUser(user.name)
    }

    override suspend fun insertFoodBroList(foodBroList: FoodBroList) {
        dao.insert(foodBroList)
    }

    override suspend fun deleteFoodBroList(foodBroList: FoodBroList) {
        dao.delete(foodBroList)
    }
}