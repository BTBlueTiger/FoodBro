package com.bluetiger.foodbrocompose.database.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bluetiger.foodbrocompose.database.room.common.SerializeAbleListConverter
import com.bluetiger.foodbrocompose.feature_open_food_facts.data.local.data_source.OpenFoodFactsDao
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model.OpenFoodFactsData
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model.ecoscore.EcoScoreDataConverter
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model.nutrient_levels.NutrientLevelsDataConverter
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model.nutriments.NutrimentsDataConverter
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model.nutriscore_data.NutriScoreDataConverter
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model.product_general.ProductGeneralDataConverter
import com.bluetiger.foodbrocompose.feature_user.data.data_source.UserActivityInformationDao
import com.bluetiger.foodbrocompose.feature_user.data.data_source.UserPersonalInformationDao
import com.bluetiger.foodbrocompose.feature_user.domain.model.UserActivityInformation
import com.bluetiger.foodbrocompose.feature_user.domain.model.UserPersonalInformation
import com.bluetiger.foodbrocompose.feature_user_list.data.data_source.FoodBroListDao
import com.bluetiger.foodbrocompose.feature_user_list.domain.model.FoodBroList

@Database(
    entities = [
        UserPersonalInformation::class,
        UserActivityInformation::class,
        OpenFoodFactsData::class,
        FoodBroList::class
    ],
    version = 4,
    exportSchema = false
)
@TypeConverters(
    EcoScoreDataConverter::class,
    NutrientLevelsDataConverter::class,
    NutrimentsDataConverter::class,
    NutriScoreDataConverter::class,
    ProductGeneralDataConverter::class,
    SerializeAbleListConverter::class
)
abstract class FoodBroDataBase : RoomDatabase() {
    abstract val userActivityInformationDao: UserActivityInformationDao
    abstract val userPersonalInformationDao: UserPersonalInformationDao
    abstract val foodFactsDao: OpenFoodFactsDao
    abstract val foodBroListDao: FoodBroListDao

    companion object {
        val DATABASE_NAME = "FoodBro.db"
    }
}