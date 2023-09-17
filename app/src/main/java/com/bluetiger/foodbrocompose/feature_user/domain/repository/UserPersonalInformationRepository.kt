package com.bluetiger.foodbrocompose.feature_user.domain.repository

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateMap
import com.bluetiger.foodbrocompose.common.FoodBroRepository
import com.bluetiger.foodbrocompose.feature_user.domain.model.Gender
import com.bluetiger.foodbrocompose.feature_user.domain.model.UserPersonalInformation
import kotlinx.coroutines.flow.Flow
import java.lang.IllegalArgumentException
import kotlin.reflect.KClass

abstract class UserPersonalInformationRepository : FoodBroRepository {

    private val _pendingNewUser = mutableStateMapOf<UserPersonalInformation.ValueType, Any>()
    val pendingNewUser : SnapshotStateMap<UserPersonalInformation.ValueType, Any> = _pendingNewUser

    fun initPendingNewUserPersonalInformation(){
        UserPersonalInformation.ValueType.values().associateWith {
            it.dataType.defaultValue()
        }.toMap().forEach{
            _pendingNewUser[it.key] = it.value
        }
    }
    private fun KClass<*>.defaultValue() = when (this) {
        String::class -> ""
        Int::class -> 0
        Long::class -> 0L
        Gender::class -> Gender.NONE
        else -> throw IllegalArgumentException("$this is not captured in default values!")
    }

    fun <T : Any> setPendingNewUserPersonalInformation(valueType: UserPersonalInformation.ValueType, newValue : T){
        _pendingNewUser[valueType] = newValue
    }

    abstract fun getUsers(): Flow<List<UserPersonalInformation>>
    abstract suspend fun getUserByName(name: String): UserPersonalInformation?
    abstract suspend fun insertUser(userPersonalInformation: UserPersonalInformation)
    abstract suspend fun deleteUser(userPersonalInformation: UserPersonalInformation)

}