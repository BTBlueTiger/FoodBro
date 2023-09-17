package com.bluetiger.foodbrocompose.feature_user.ui.add_edit_user.components.tabs.activity.health_connect

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.bluetiger.foodbrocompose.feature_user.ui.add_edit_user.components.tabs.activity.AddEditUserActivityViewModel
import com.bluetiger.foodbrocompose.ui.common.components.headline.HeadLine

@Composable
fun HealthConnectActivityValuesSheet(
    addEditUserActivityViewModel: AddEditUserActivityViewModel = hiltViewModel()
){
    HeadLine(headline = "HealthConnect")
}