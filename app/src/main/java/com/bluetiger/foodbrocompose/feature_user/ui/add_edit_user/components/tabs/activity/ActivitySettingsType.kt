package com.bluetiger.foodbrocompose.feature_user.ui.add_edit_user.components.tabs.activity

import androidx.compose.runtime.Composable
import com.bluetiger.foodbrocompose.feature_user.ui.add_edit_user.components.tabs.activity.customized.CustomizedActivityValuesSheet
import com.bluetiger.foodbrocompose.feature_user.ui.add_edit_user.components.tabs.activity.health_connect.HealthConnectActivityValuesSheet
import com.bluetiger.foodbrocompose.feature_user.ui.add_edit_user.components.tabs.activity.pre_configured.PreConfiguredActivityValuesSheet

sealed class ActivitySettingsType {

    abstract val shortTerm: String

    @Composable
    abstract fun Sheet(addEditUserActivityViewModel: AddEditUserActivityViewModel): Unit

    object PreConfigured : ActivitySettingsType() {
        override val shortTerm: String
            get() = "Pre Configured"

        @Composable
        override fun Sheet(addEditUserActivityViewModel: AddEditUserActivityViewModel) =
            PreConfiguredActivityValuesSheet(addEditUserActivityViewModel)
    }

    object Customized : ActivitySettingsType() {
        override val shortTerm: String
            get() = "Customized"

        @Composable
        override fun Sheet(addEditUserActivityViewModel: AddEditUserActivityViewModel) =
            CustomizedActivityValuesSheet(addEditUserActivityViewModel)
    }

    object HealthConnect : ActivitySettingsType() {
        override val shortTerm: String
            get() = "Health Connect"

        @Composable
        override fun Sheet(addEditUserActivityViewModel: AddEditUserActivityViewModel) =
            HealthConnectActivityValuesSheet(addEditUserActivityViewModel)
    }
}