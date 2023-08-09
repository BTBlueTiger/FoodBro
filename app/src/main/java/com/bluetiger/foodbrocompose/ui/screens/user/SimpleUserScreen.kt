package com.bluetiger.foodbrocompose.ui.screens.user

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.DateRange
import androidx.compose.material.icons.twotone.Person
import androidx.compose.material3.Divider
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bluetiger.foodbrocompose.Graph
import com.bluetiger.foodbrocompose.R
import com.bluetiger.foodbrocompose.database.user.PersonDataValueType
import com.bluetiger.foodbrocompose.database.user.User
import com.bluetiger.foodbrocompose.database.user.UserStore
import com.bluetiger.foodbrocompose.ui.common.selection.Selection
import com.bluetiger.foodbrocompose.ui.common.selection.SelectionGroupMultiSelect
import com.bluetiger.foodbrocompose.ui.common.stateable.StateAble
import com.bluetiger.foodbrocompose.utility.toLocalDate
import kotlinx.coroutines.launch

private class SimpleUserViewModel(
    private val userStore: UserStore = Graph.userStore
) : ViewModel() {

    fun onCreateNewUserClicked() {

        viewModelScope.launch {
            userStore.getUserByEmail(getValue(PersonDataValueType.EMAIL)).collect{
                if(it == null){
                    userStore.addUser(User(
                        email = getValue(PersonDataValueType.EMAIL),
                        height = getValue(PersonDataValueType.HEIGHT).toInt(),
                        weight = getValue(PersonDataValueType.WEIGHT).toInt(),
                        birthday = getValue(PersonDataValueType.BIRTHDAY).toLong(),
                        gender = getValue(PersonDataValueType.GENDER)
                    ))
                } else {
                    Log.e("User", it.toString())
                }
            }
        }
    }

    fun mapToUser() = User(
        email = person.getValue(PersonDataValueType.EMAIL).getValue(),
        height = person.getValue(PersonDataValueType.HEIGHT).getValue().toInt(),
        weight = person.getValue(PersonDataValueType.WEIGHT).getValue().toInt(),
        birthday = person.getValue(PersonDataValueType.BIRTHDAY).getValue().toLong(),
        gender = person.getValue(PersonDataValueType.GENDER).getValue()
    )

    fun userToMap(user: User) {
        setValue(PersonDataValueType.EMAIL, user.email)
        setValue(PersonDataValueType.HEIGHT, user.height.toString())
        setValue(PersonDataValueType.WEIGHT, user.weight.toString())
        setValue(PersonDataValueType.BIRTHDAY, user.birthday.toString())
        setValue(PersonDataValueType.GENDER, user.gender)
    }

    val person = mutableStateMapOf(
        Pair(PersonDataValueType.EMAIL, StateAble.Model("", StateAble.State.DEFAULT)),
        Pair(PersonDataValueType.HEIGHT, StateAble.Model("", StateAble.State.DEFAULT)),
        Pair(PersonDataValueType.WEIGHT, StateAble.Model("", StateAble.State.DEFAULT)),
        Pair(PersonDataValueType.BIRTHDAY, StateAble.Model("", StateAble.State.DEFAULT)),
        Pair(PersonDataValueType.GENDER, StateAble.Model("", StateAble.State.DEFAULT))
    )

    fun setValue(valueType: PersonDataValueType, value: String) {
        person[valueType]?.setValue(value)
        if(value.isEmpty()){
            setState(valueType, StateAble.State.DEFAULT)
        } else {
            setState(valueType, StateAble.State.FILLED)
        }
    }

    fun setState(valueType: PersonDataValueType, state: StateAble.State) {
        person[valueType]?.setState(state)
    }
    fun getValue(valueType: PersonDataValueType): String = person[valueType]?.getValue() ?: ""
    fun getState(valueType: PersonDataValueType): StateAble.State =
        person[valueType]?.getState() ?: StateAble.State.DEFAULT
}

private val model = SimpleUserViewModel()

@Composable
private fun SimpleDivider() {
    Divider(modifier = Modifier.padding(top = 20.dp, bottom = 20.dp))
}

@Composable
fun SimpleUserScreen(
    user: User = User.DEMO,
    floatingButtonText: String = "",
    onFloatingButtonClicked: (User) -> Unit
) {

    if(user != User.DEMO){
        model.userToMap(user)
    } else {
        model.person.values.forEach { it.setValue("") }
    }

    val snackBarHostState = SnackbarHostState()

    Scaffold (snackbarHost = { SnackbarHost(snackBarHostState) },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                modifier = Modifier.padding(20.dp),
                onClick = { onFloatingButtonClicked(model.mapToUser()) }
            ) { Text(floatingButtonText) }
        },
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                UserContent()
            }
        }
    )
}

@Composable
fun UserContent() {

    Column(Modifier.padding(15.dp)) {

        PersonalInformation()

        SimpleDivider()

        DatePickerDialog()

        SimpleDivider()

        GenderSelection()

        SimpleDivider()
    }
}

@Composable
fun IconWithRows(
    image: ImageVector,
    rowList: @Composable () -> List<Unit>
) {
    Column {
        Row {
            Column(Modifier.padding(10.dp)) {
                Icon(image, contentDescription = "")
            }
            Column(Modifier.weight(1f)) {
                rowList.invoke()
            }
        }
    }
}
@Composable
fun PersonalInformation() {

    IconWithRows(image = Icons.TwoTone.Person, rowList = {
        listOf(
            Row(Modifier.fillMaxWidth()) {
                SimpleStateOutlinedTextField(Modifier, PersonDataValueType.EMAIL)
            },
            Row(Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    SimpleStateOutlinedTextField(
                        Modifier.fillMaxWidth(),
                        PersonDataValueType.HEIGHT
                    )
                }
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    SimpleStateOutlinedTextField(
                        Modifier
                            .fillMaxWidth(0.9f),
                        PersonDataValueType.WEIGHT
                    )
                }
            },
        )
    })
}


@Composable
fun SimpleStateOutlinedTextField(
    modifier: Modifier,
    valueTyp: PersonDataValueType
) {
    StateAble(
        value = model.getValue(valueTyp),
        label = valueTyp.label,
        placeholder = valueTyp.placeHolder,
        modifier = modifier,
        state = model.getState(valueTyp),
        onValueChange = { model.setValue(valueTyp, it) }
    ).TextField()
}

@Composable
fun DatePickerDialog() {
    IconWithRows(image = Icons.TwoTone.DateRange) {
        listOf(
            Row(Modifier.fillMaxWidth()) {
                SimpleStateDatePicker(modifier = Modifier, valueTyp = PersonDataValueType.BIRTHDAY)
            }
        )
    }
}


@Composable
fun SimpleStateDatePicker(
    modifier: Modifier,
    valueTyp: PersonDataValueType
) {
    StateAble(
        value = model.getValue(valueTyp),
        label = valueTyp.label,
        placeholder = valueTyp.placeHolder,
        modifier = modifier,
        state = model.getState(valueTyp),
        onValueChange = { model.setValue(valueTyp, it) }
    ).DatePicker()
}


@Composable
fun GenderSelection() {
    StateAble(
        label = PersonDataValueType.GENDER.label,
        value = "",
        modifier = Modifier,
        onValueChange = { model.setValue(PersonDataValueType.GENDER, it) }
    ).StateAbleSelectionGroup(
        selections = listOf(
            Selection("Male", R.drawable.baseline_male_24, false),
            Selection("Female", R.drawable.baseline_female_24, false)
        ),
        rows = 1
    )
}


@Composable
fun SpecialFemale() {

    //HeadLine(headline = "Babys?")

    SelectionGroupMultiSelect(
        modifier = Modifier,
        selections = listOf(
            Selection("Pregnant", R.drawable.baseline_pregnant_woman_24, false),
            Selection("Breastfeeding", R.drawable.baseline_face_5_24, false)
        ),
        rows =1,
        onValueChange = {
            Log.e("Test", it.toString())
        }
    )
}