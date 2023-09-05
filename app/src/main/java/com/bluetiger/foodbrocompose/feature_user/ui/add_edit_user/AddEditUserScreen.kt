package com.bluetiger.foodbrocompose.feature_user.ui.add_edit_user

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Check
import androidx.compose.material.icons.twotone.DateRange
import androidx.compose.material.icons.twotone.Person
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bluetiger.foodbrocompose.R
import com.bluetiger.foodbrocompose.feature_user.domain.model.Gender
import com.bluetiger.foodbrocompose.feature_user.domain.model.User
import com.bluetiger.foodbrocompose.feature_user.ui.add_edit_user.components.LeftColIconRightColRows
import com.bluetiger.foodbrocompose.ui.common.components.headline.HeadLine
import com.bluetiger.foodbrocompose.ui.common.components.textfield.outline_textfield.color_state.ConditionOutlineTextField
import com.bluetiger.foodbrocompose.ui.common.components.selection.SelectionGroupSingleSelect
import com.bluetiger.foodbrocompose.ui.common.components.selection.SelectionModel
import com.bluetiger.foodbrocompose.ui.common.components.textfield.outline_textfield.DatePickerTextField
import com.bluetiger.foodbrocompose.ui.common.components.textfield.outline_textfield.color_state.ConditionOutlineTextFieldPack
import com.bluetiger.foodbrocompose.utility.ConverterUtility
import kotlinx.coroutines.launch


@Composable
private fun <T> ColorStateOutlineTextFieldBuilder(
    state: ConditionOutlineTextFieldPack<T>,
    onValueChange: (String) -> Unit,
    valueType: User.ValueType,
){
    ConditionOutlineTextField(
        state = state,
        onValueChange = onValueChange,
        suffix = { Text(text = valueType.unit) },
        label = { Text(text = valueType.label) },
        placeholder = { Text(text = valueType.placeHolder) })
}

@Composable
fun AddEditUserScreen(
    navigateToHome: () -> Unit,
    email : String = "",
    viewModel: AddEditUserViewModel = hiltViewModel()
) {

    if(email.isNotEmpty()){
        viewModel.onEvent(AddEditUserEvent.EditUser(email))
    }

    val emailState = viewModel.email.value
    val heightState = viewModel.height.value
    val weightState = viewModel.weight.value
    val genderState = viewModel.gender.value
    val birthdayState = viewModel.birthday.value
    val onSaveUserRequest = viewModel.onSaveUserRequest.value

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    scope.launch {
                        Log.e("RegisterEvent", "test")
                        viewModel.onEvent(AddEditUserEvent.SaveUser)
                        if(onSaveUserRequest.success){
                            navigateToHome()
                        } else {
                            onSaveUserRequest.snackBarMessage?.let {
                                snackbarHostState.showSnackbar(it)
                            }
                        }
                    }
                },
            ) {
                Icon(
                    imageVector = Icons.TwoTone.Check,
                    contentDescription = "Save note"
                )
            }

        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Column(Modifier.padding(15.dp)) {

                // Personal Information
                LeftColIconRightColRows(imageVector = Icons.TwoTone.Person) {
                    listOf(
                        // Email
                        Row(Modifier.fillMaxWidth()) {
                            ColorStateOutlineTextFieldBuilder(
                                state = emailState,
                                onValueChange = {
                                    viewModel.onEvent(
                                        AddEditUserEvent.EnteredValue(it, User.ValueType.EMAIL)
                                    )
                                }, valueType = User.ValueType.EMAIL
                            )
                        },
                        // Height
                        Row(Modifier.fillMaxWidth()) {

                            ColorStateOutlineTextFieldBuilder(
                                state = heightState,
                                onValueChange = {
                                    if (it.toIntOrNull() != null) {
                                        viewModel.onEvent(
                                            AddEditUserEvent.EnteredValue(
                                                it.toInt(),
                                                User.ValueType.HEIGHT
                                            )
                                        )
                                    }
                                },
                                valueType = User.ValueType.HEIGHT
                            )
                        },
                        // Weight
                        Row(Modifier.fillMaxWidth()) {
                            ColorStateOutlineTextFieldBuilder(
                                state = weightState,
                                onValueChange = {
                                    if (it.toIntOrNull() != null) {
                                        viewModel.onEvent(
                                            AddEditUserEvent.EnteredValue(
                                                it.toInt(),
                                                User.ValueType.WEIGHT
                                            )
                                        )
                                    }
                                },
                                valueType = User.ValueType.WEIGHT
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
                                value = birthdayState.value,
                                label = { Text(text = "Birthday") },
                                placeholder = { Text(text = "28.08.1995") },
                                modifier = Modifier,
                                onValueChange = {
                                    Log.e("Time", it.toString())
                                    viewModel.onEvent(
                                        AddEditUserEvent.EnteredValue(
                                            it,
                                            User.ValueType.BIRTHDAY
                                        )
                                    )
                                },
                                colors = birthdayState.colorCase.color(),
                            )
                        }
                    )
                }

                Divider(modifier = Modifier.padding(top = 20.dp, bottom = 20.dp))

                HeadLine(headline = "Gender")

                Divider(modifier = Modifier.padding(top = 20.dp, bottom = 20.dp))

                SelectionGroupSingleSelect(
                    value = genderState.value,
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
                                User.ValueType.GENDER
                            )
                        )
                    }
                )
            }

        }
    }
}

private fun String.emptyIfZero() = if (this == "0") "" else this
