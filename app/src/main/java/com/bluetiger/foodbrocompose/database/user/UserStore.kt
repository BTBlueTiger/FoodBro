package com.bluetiger.foodbrocompose.database.user

import kotlinx.coroutines.flow.Flow

class UserStore(
    private val userDao: UserDao
) {
    fun getUserByEmail(email: String) : Flow<User?> = userDao.getUserByEmail(email)

    fun getAllUser() : Flow<List<User>> = userDao.getAllUser()

    suspend fun addUser(user: User){
        userDao.insert(user)
    }
}