package com.bluetiger.foodbrocompose

import android.Manifest
import android.app.Application
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import com.bluetiger.foodbrocompose.database.FBPreferences
import com.bluetiger.foodbrocompose.feature_user.domain.model.Gender
import dagger.hilt.android.HiltAndroidApp
import java.lang.IllegalArgumentException
import kotlin.reflect.KClass

@HiltAndroidApp
class FoodBroApplication : Application(){}

fun KClass<*>.defaultValue() = when (this) {
    String::class -> ""
    Int::class -> 0
    Long::class -> 0L
    Gender::class -> Gender.NONE
    else -> throw IllegalArgumentException("$this is not captured in default values!")
}