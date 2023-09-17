package com.bluetiger.foodbrocompose.feature_user.data.data_source

import androidx.room.Dao
import androidx.room.Query
import com.bluetiger.foodbrocompose.database.room.BaseDao
import com.bluetiger.foodbrocompose.feature_user.domain.model.UserPersonalInformation
import kotlinx.coroutines.flow.Flow

@Dao
interface UserPersonalInformationDao : BaseDao<UserPersonalInformation> {

    @Query("SELECT * FROM user WHERE name = :name")
    suspend fun getUserByName(name: String): UserPersonalInformation?

    @Query("SELECT * FROM user")
    fun getAllUser(): Flow<List<UserPersonalInformation>>

}