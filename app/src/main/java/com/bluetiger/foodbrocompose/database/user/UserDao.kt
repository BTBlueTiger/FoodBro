package com.bluetiger.foodbrocompose.database.user

import androidx.room.Dao
import androidx.room.Query
import com.bluetiger.foodbrocompose.database.room.BaseDao
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao : BaseDao<User> {

    @Query("SELECT * FROM user WHERE email = :email")
    abstract fun getUserByEmail(email: String): Flow<User>

    @Query("SELECT * FROM user")
    abstract fun getAllUser(): Flow<List<User>>

    // Add more queries here if needed
}