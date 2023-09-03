package com.bluetiger.foodbrocompose

import android.Manifest
import android.app.Application
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import com.bluetiger.foodbrocompose.database.FBPreferences
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FoodBroApplication : Application(){}

