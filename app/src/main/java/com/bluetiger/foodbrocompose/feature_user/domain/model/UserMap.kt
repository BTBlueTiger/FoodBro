package com.bluetiger.foodbrocompose.feature_user.domain.model

import androidx.compose.runtime.mutableStateMapOf
import kotlin.reflect.KClass

class UserMap {

    private val map: MutableMap<KClass<out UserInformation>, UserInformation> = mutableStateMapOf(
        Pair(UserPersonalInformation::class, UserPersonalInformation()),
        Pair(UserActivityInformation::class, UserActivityInformation()),
        Pair(UserNutritionSettingInformation::class, UserNutritionSettingInformation())
    )

    operator fun <T : UserInformation> get(userInformation: KClass<T>): UserInformation {
        return map[userInformation]!!
    }

    operator fun <T : UserInformation> set(userInformation: KClass<T>, data: T) {
        map[userInformation] = data
    }

    var isPending = false
}