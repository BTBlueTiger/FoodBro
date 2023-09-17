package com.bluetiger.foodbrocompose.feature_user.data.data_source

import androidx.room.Dao
import androidx.room.Query
import com.bluetiger.foodbrocompose.database.room.BaseDao
import com.bluetiger.foodbrocompose.feature_user.domain.model.UserActivityInformation

@Dao
interface UserActivityInformationDao: BaseDao<UserActivityInformation> {

    @Query("SELECT * FROM user_activity WHERE userName = :userPersonalName")
    suspend fun getUserActivityLevels(userPersonalName: String) : UserActivityInformation
}