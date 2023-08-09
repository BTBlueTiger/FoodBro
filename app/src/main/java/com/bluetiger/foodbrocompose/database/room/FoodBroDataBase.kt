package com.bluetiger.foodbrocompose.database.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bluetiger.foodbrocompose.database.user.User
import com.bluetiger.foodbrocompose.database.user.UserDao

@Database(
    entities = [
        User::class
    ],
    version = 1,
    exportSchema = false
)
abstract class FoodBroDataBase : RoomDatabase() {
    abstract fun userDao() : UserDao
}