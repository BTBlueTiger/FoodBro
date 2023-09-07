package com.bluetiger.foodbrocompose.database.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bluetiger.foodbrocompose.feature_open_food_facts.data.local.data_source.OpenFoodFactsDao
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model.OpenFoodFactsData
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model.ecoscore.EcoScoreDataConverter
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model.nutrient_levels.NutrientLevelsDataConverter
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model.nutriments.NutrimentsDataConverter
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model.nutriscore_data.NutriScoreDataConverter
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model.product_general.ProductGeneralDataConverter
import com.bluetiger.foodbrocompose.feature_user.data.data_source.UserDao
import com.bluetiger.foodbrocompose.feature_user.domain.model.User
import com.bluetiger.foodbrocompose.feature_user_list.data.data_source.FoodBroListDao

@Database(
    entities = [
        User::class,
        OpenFoodFactsData::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    EcoScoreDataConverter::class,
    NutrientLevelsDataConverter::class,
    NutrimentsDataConverter::class,
    NutriScoreDataConverter::class,
    ProductGeneralDataConverter::class
)
abstract class FoodBroDataBase : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val foodFactsDao: OpenFoodFactsDao
    abstract val foodBroListDao: FoodBroListDao

    companion object {
        val DATABASE_NAME = "FoodBro.db"
    }
}