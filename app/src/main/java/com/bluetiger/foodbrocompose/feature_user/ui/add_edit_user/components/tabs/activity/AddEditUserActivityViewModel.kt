package com.bluetiger.foodbrocompose.feature_user.ui.add_edit_user.components.tabs.activity

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.runtime.toMutableStateMap
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.bluetiger.foodbrocompose.feature_user.domain.model.UserActivityInformation
import com.bluetiger.foodbrocompose.feature_user.domain.use_case.UserUseCases
import com.bluetiger.foodbrocompose.ui.common.components.slider.SliderStateColors
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddEditUserActivityViewModel @Inject constructor(
    private val userUseCases: UserUseCases,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val TAG = this::class.toString()

    private val pendingMap by lazy { userUseCases.pendingInformation.newUser.getValue() }
    private var newUserActivity =
        pendingMap[UserActivityInformation::class] as UserActivityInformation

    private val _preConfigured = mutableStateOf(UserActivityInformation.ValueType.SLEEP)
    val preConfigured: State<UserActivityInformation.ValueType> = _preConfigured

    private val _activityInformation = UserActivityInformation.ValueType.values().map {
        it to 0f
    }.toMutableStateMap()

    val activityInformation: SnapshotStateMap<UserActivityInformation.ValueType, Float> =
        _activityInformation

    val userOptionList = listOf(
        ActivitySettingsType.PreConfigured,
        ActivitySettingsType.Customized,
        ActivitySettingsType.HealthConnect
    )

    private val _selectedOptionState = mutableStateOf(userOptionList[0])
    val selectedOptionState: State<ActivitySettingsType> = _selectedOptionState

    init {

        Log.e("Tag", newUserActivity.preConfigured)
        when (newUserActivity.preConfigured) {
            "" -> {
                _selectedOptionState.value = ActivitySettingsType.Customized
                newUserActivity.iterator().forEach {
                    activityInformation[it.first] = it.second
                }
            }

            "health_connect" -> {
                _selectedOptionState.value = ActivitySettingsType.HealthConnect
            }

            else -> {
                _selectedOptionState.value = ActivitySettingsType.PreConfigured
                _preConfigured.value = UserActivityInformation.ValueType.values()
                    .firstOrNull() { it.shortTerm == newUserActivity.preConfigured }
                    ?: UserActivityInformation.ValueType.SLEEP
            }
        }


        if (pendingMap.isPending) {


            Log.i(TAG, "Saved Activity Information $activityInformation")

            if (newUserActivity.preConfigured != "") {
                _preConfigured.value = UserActivityInformation.ValueType.values()
                    .first { it.shortTerm == newUserActivity.preConfigured }
            }
            Log.i(
                TAG,
                "Preconfigured Value=[$preConfigured] ShortTerm=[${newUserActivity.preConfigured}] "
            )
        }
    }

    fun getSliderColor(): SliderStateColors {
        val sum = activityInformation.values.sum().toInt()
        val maxvalue = 24 * 60
        return when {
            sum == maxvalue -> {
                SliderStateColors.VALID
            }
            sum > maxvalue -> {
                SliderStateColors.ERROR
            }
            sum < maxvalue -> {
                SliderStateColors.PENDING
            }
            else -> {
                SliderStateColors.DEFAULT
            }
        }
    }

    fun getTimeDifference() = (24f * 60) - activityInformation.values.sum()

    private fun updatePendingMap(userActivityInformation: UserActivityInformation) {
        pendingMap[UserActivityInformation::class] = userActivityInformation
        Log.i(TAG, newUserActivity.toString())
        userUseCases.pendingInformation.newUser.setValue(pendingMap)
    }

    fun onEvent(event: AddEditUserActivityEvent) {
        when (event) {

            is AddEditUserActivityEvent.SelectedOptionChanged -> {
                _selectedOptionState.value = event.option
            }

            is AddEditUserActivityEvent.PreconfiguredActivityValueChanged -> {
                _preConfigured.value = event.preconfiguredType
                newUserActivity =
                    newUserActivity.copy(preConfigured = event.preconfiguredType.shortTerm)
                updatePendingMap(newUserActivity)
            }

            is AddEditUserActivityEvent.CustomActivityValueChanged -> {
                activityInformation[event.userActivityInformationValueType] = event.value
                if (newUserActivity.preConfigured != "") {
                    newUserActivity = newUserActivity.copy(preConfigured = "")
                }
                newUserActivity = newUserActivity.customCopy(
                    event.userActivityInformationValueType.memberParam, event.value
                ) as UserActivityInformation
                updatePendingMap(newUserActivity)
            }
        }
    }
}