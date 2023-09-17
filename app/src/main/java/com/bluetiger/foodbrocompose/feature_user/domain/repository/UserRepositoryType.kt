package com.bluetiger.foodbrocompose.feature_user.domain.repository

import com.bluetiger.foodbrocompose.common.FoodBroRepository
import kotlin.reflect.KClass

enum class UserRepositoryType() {
    UserPersonalInformation(),
    UserActivityInformation(),
}