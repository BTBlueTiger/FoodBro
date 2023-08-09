package com.bluetiger.foodbrocompose

import android.content.Context
import androidx.room.Room
import com.bluetiger.foodbrocompose.database.room.FoodBroDataBase
import com.bluetiger.foodbrocompose.database.user.User
import com.bluetiger.foodbrocompose.database.user.UserStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.io.File

object Graph {
    lateinit var database: FoodBroDataBase
        private set

    val userStore by lazy {
        UserStore(userDao = database.userDao())
    }

    private val _user = MutableStateFlow(User(""))

    val user: StateFlow<User>
        get() = _user

    fun provide(context: Context) {
        database = Room.databaseBuilder(context, FoodBroDataBase::class.java, "data.db")
            // This is not recommended for normal apps, but the goal of this sample isn't to
            // showcase all of Room.
            .fallbackToDestructiveMigration()
            .build()
    }
}