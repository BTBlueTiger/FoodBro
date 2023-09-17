package com.bluetiger.foodbrocompose.feature_user.domain.model

import java.lang.IllegalArgumentException
import kotlin.reflect.KClass
import kotlin.reflect.KParameter
import kotlin.reflect.full.instanceParameter
import kotlin.reflect.full.memberFunctions

interface UserInformation {
    fun <T> customCopy(memberParam: String, value: T): UserInformation {

        val copy = this::class.memberFunctions.single { it.name == "copy" }

        val instanceParam = copy.instanceParameter ?: throw IllegalArgumentException("Instance parameter not found")
        val propertyParam = copy.parameters.single { it.name == memberParam }

        val updatedCopy = copy.callBy(
            mapOf(
                instanceParam to this,
                propertyParam to value
            )
        )
        return updatedCopy as UserInformation
    }

    fun customCopy(vararg pair: Pair<String, Any>): UserInformation {

        val copy = this::class.memberFunctions.single { it.name == "copy" }

        val instanceParam = copy.instanceParameter ?: throw IllegalArgumentException("Instance parameter not found")
        val propertyMap = mutableMapOf<KParameter, Any>()

        pair.forEach { params ->
            val param = copy.parameters.singleOrNull { it.name == params.first }
            if (param != null) {
                propertyMap[param] = params.second
            }
        }

        propertyMap[instanceParam] = this

        val updatedCopy = copy.callBy(propertyMap.toMap())
        return updatedCopy as UserInformation
    }


}