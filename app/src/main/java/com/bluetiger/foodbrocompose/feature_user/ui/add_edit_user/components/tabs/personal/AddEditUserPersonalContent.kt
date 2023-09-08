package com.bluetiger.foodbrocompose.feature_user.ui.add_edit_user.components.tabs.personal

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.DateRange
import androidx.compose.material.icons.twotone.Person
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bluetiger.foodbrocompose.R
import com.bluetiger.foodbrocompose.feature_user.domain.model.Gender
import com.bluetiger.foodbrocompose.feature_user.domain.model.UserPersonal
import com.bluetiger.foodbrocompose.feature_user.ui.add_edit_user.AddEditUserEvent
import com.bluetiger.foodbrocompose.feature_user.ui.add_edit_user.components.LeftColIconRightColRows
import com.bluetiger.foodbrocompose.ui.common.components.headline.HeadLine
import com.bluetiger.foodbrocompose.ui.common.components.selection.SelectionGroupSingleSelect
import com.bluetiger.foodbrocompose.ui.common.components.selection.SelectionModel
import com.bluetiger.foodbrocompose.ui.common.components.textfield.outline_textfield.DatePickerTextField
import com.bluetiger.foodbrocompose.ui.common.components.textfield.outline_textfield.color_state.ConditionOutlineTextField
import com.bluetiger.foodbrocompose.ui.common.components.textfield.outline_textfield.color_state.ConditionOutlineTextFieldPack
import com.bluetiger.foodbrocompose.ui.common.components.textfield.outline_textfield.colors.OutlineTextFieldColorCases

@Composable
fun ColorStateOutlineTextFieldBuilder(
    state: ConditionOutlineTextFieldPack<*>?,
    onValueChange: (String) -> Unit,
    valueType: UserPersonal.ValueType,
) {
    ConditionOutlineTextField(
        state = state ?: ConditionOutlineTextFieldPack<String>(""),
        onValueChange = onValueChange,
        suffix = { Text(text = valueType.unit) },
        label = { Text(text = valueType.label) },
        placeholder = { Text(text = valueType.placeHolder) })
}

@Composable
fun AddEditUserPersonalTabRow(
    viewModel: AddEditUserPersonalContentViewModel = hiltViewModel()
) {

    val personalInformationMap = viewModel.personalInformation.toMap()

    Box(
        modifier = Modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(
            Modifier
                .padding(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(5.dp))

            HeadLine(headline = "Personal Information")

            Spacer(modifier = Modifier.height(5.dp))

            // Personal Information
            LeftColIconRightColRows(imageVector = Icons.TwoTone.Person) {
                listOf(
                    // Email
                    Row(Modifier.fillMaxWidth()) {
                        ColorStateOutlineTextFieldBuilder(
                            state = personalInformationMap[UserPersonal.ValueType.NAME],
                            onValueChange = {
                                viewModel.onEvent(
                                    AddEditUserEvent.EnteredValue(it, UserPersonal.ValueType.NAME)
                                )
                            }, valueType = UserPersonal.ValueType.NAME
                        )
                    },
                    // Height
                    Row(Modifier.fillMaxWidth()) {

                        ColorStateOutlineTextFieldBuilder(
                            state = personalInformationMap[UserPersonal.ValueType.HEIGHT],
                            onValueChange = {
                                if (it.toIntOrNull() != null) {
                                    viewModel.onEvent(
                                        AddEditUserEvent.EnteredValue(
                                            it.toInt(),
                                            UserPersonal.ValueType.HEIGHT
                                        )
                                    )
                                }
                            },
                            valueType = UserPersonal.ValueType.HEIGHT
                        )
                    },
                    // Weight
                    Row(Modifier.fillMaxWidth()) {
                        ColorStateOutlineTextFieldBuilder(
                            state = personalInformationMap[UserPersonal.ValueType.WEIGHT],
                            onValueChange = {
                                if (it.toIntOrNull() != null) {
                                    viewModel.onEvent(
                                        AddEditUserEvent.EnteredValue(
                                            it.toInt(),
                                            UserPersonal.ValueType.WEIGHT
                                        )
                                    )
                                }
                            },
                            valueType = UserPersonal.ValueType.WEIGHT
                        )
                    }
                )
            }

            Divider(modifier = Modifier.padding(top = 20.dp, bottom = 20.dp))

            // Date
            LeftColIconRightColRows(imageVector = Icons.TwoTone.DateRange) {
                listOf(
                    Row(Modifier.fillMaxWidth()) {
                        DatePickerTextField(
                            value = personalInformationMap[UserPersonal.ValueType.BIRTHDAY]?.toValue() ?: 0,
                            label = { Text(text = "Birthday") },
                            placeholder = { Text(text = "28.08.1995") },
                            modifier = Modifier,
                            onValueChange = {
                                Log.e("Time", it.toString())
                                viewModel.onEvent(
                                    AddEditUserEvent.EnteredValue(
                                        it,
                                        UserPersonal.ValueType.BIRTHDAY
                                    )
                                )
                            },
                            colors = personalInformationMap[UserPersonal.ValueType.NAME]?.colorCase?.color()
                                ?: OutlineTextFieldColorCases.DEFAULT.color(),
                        )
                    }
                )
            }

            Divider(modifier = Modifier.padding(top = 10.dp, bottom = 10.dp))

            HeadLine(headline = "Gender")

            Divider(modifier = Modifier.padding(top = 10.dp, bottom = 10.dp))

            SelectionGroupSingleSelect(
                value = personalInformationMap[UserPersonal.ValueType.GENDER]?.value?: Gender.NONE,
                modifier = Modifier,
                selections = listOf(
                    SelectionModel("Male", R.drawable.baseline_male_24, Gender.MALE),
                    SelectionModel("Female", R.drawable.baseline_female_24, Gender.FEMALE)
                ),
                rows = 1,
                onValueChange = {
                    viewModel.onEvent(
                        AddEditUserEvent.EnteredValue(
                            it,
                            UserPersonal.ValueType.GENDER
                        )
                    )
                }
            )
        }
    }
}