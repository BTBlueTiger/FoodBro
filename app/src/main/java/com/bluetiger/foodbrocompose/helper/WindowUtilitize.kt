package com.bluetiger.foodbrocompose.helper

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

object WindowUtilities {

    @Composable
    fun getPercentWidthToScreenWidth(width: Float) = LocalConfiguration.current.screenWidthDp.times(width).dp
}