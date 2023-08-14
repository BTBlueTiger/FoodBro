package com.bluetiger.foodbrocompose.database.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bluetiger.foodbrocompose.feature_user.data.data_source.UserDao
import com.bluetiger.foodbrocompose.feature_user.domain.model.User

@Database(
    entities = [
        User::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class FoodBroDataBase : RoomDatabase() {
    abstract val userDao : UserDao

    companion object{
        val DATABASE_NAME = "FoodBro.db"
    }
}