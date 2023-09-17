package com.bluetiger.foodbrocompose.feature_user.ui.add_edit_user.components.tabs.activity

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.runtime.toMutableStateMap
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.bluetiger.foodbrocompose.feature_user.domain.model.UserActivityInformation
import com.bluetiger.foodbrocompose.feature_user.domain.use_case.UserUseCases
import com.bluetiger.foodbrocompose.feature_user.domain.use_case.user_activity_informations.UserActivityInformationUseCases
import com.bluetiger.foodbrocompose.feature_user.ui.add_edit_user.components.tabs.AddEditUserTab
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddEditUserActivityViewModel @Inject constructor(
    private val userUseCases: UserUseCases,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val pendingMap by lazy { userUseCases.pendingInformation.newUser.getValue() }
    private val newUserActivity by lazy { pendingMap[UserActivityInformation::class] as UserActivityInformation }

    private val _preConfigured = mutableStateOf(UserActivityInformation.ValueType.SLEEP)
    val preConfigured: State<UserActivityInformation.ValueType> = _preConfigured

    private val _activityInformation = UserActivityInformation.ValueType.values().map {
        it to 0f
    }.toMutableStateMap()

    val activityInformation: SnapshotStateMap<UserActivityInformation.ValueType, Float> =
        _activityInformation

    init {

        Log.e("Event", newUserActivity.preConfigured)
        if (pendingMap.isPending) {
            val iterator = newUserActivity.iterator()
            UserActivityInformation.ValueType.values().forEach {
                activityInformation[it] = iterator.next()
            }
            if (newUserActivity.preConfigured != "") {
                _preConfigured.value = UserActivityInformation.ValueType.values()
                    .first { it.shortTerm == newUserActivity.preConfigured }
            }
        }
    }

    fun onEvent(event: AddEditUserActivityEvent) {
        when (event) {
            is AddEditUserActivityEvent.ActivityValueChanged -> {
                userUseCases.pendingInformation.newUser.getValue().isPending = true
                when (event.activitySettingsType) {
                    ActivitySettingsType.PreConfigured -> {
                        _preConfigured.value = event.userActivityInformationValueType
                        pendingMap[UserActivityInformation::class] =
                            newUserActivity.customCopy(
                                Pair("pal", event.value),
                                Pair(
                                    "preConfigured",
                                    event.userActivityInformationValueType.shortTerm
                                )
                            ) as UserActivityInformation

                    }

                    ActivitySettingsType.Customized -> {
                        _preConfigured.value = UserActivityInformation.ValueType.SLEEP
                        var pal = 0f
                        newUserActivity.iterator().forEach {
                            pal += it
                        }
                        pal /= UserActivityInformation.ValueType.values().size
                        pendingMap[UserActivityInformation::class] =
                            newUserActivity.customCopy(
                                Pair(
                                    event.userActivityInformationValueType.memberParam,
                                    event.value
                                ),
                                Pair("preConfigured", ""),
                                Pair("pal", pal)
                                ) as UserActivityInformation
                    }

                    ActivitySettingsType.HealthConnect -> {

                    }
                }
                userUseCases.pendingInformation.newUser.setValue(pendingMap)
            }
        }
    }
}