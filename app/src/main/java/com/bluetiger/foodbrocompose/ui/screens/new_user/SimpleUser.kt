package com.bluetiger.foodbrocompose.ui.screens.new_user


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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.bluetiger.foodbrocompose.R
import com.bluetiger.foodbrocompose.ui.common.headline.HeadLine
import com.bluetiger.foodbrocompose.ui.common.selection.Selection
import com.bluetiger.foodbrocompose.ui.common.stateable.StateAble
import com.bluetiger.foodbrocompose.user.PersonDataValueType
import kotlinx.coroutines.launch
import kotlin.text.StringBuilder

private val model = NewUserViewModel()

@Composable
fun NewUserUser() {

    val snackBarHostState = SnackbarHostState()
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackBarHostState) },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                modifier = Modifier.padding(20.dp),
                onClick = {
                    val list = mutableListOf<String>()
                    model.person.forEach {
                        if(it.value.errorIfState(StateAble.State.DEFAULT) || it.value.isError()){
                            list.add(it.key.label)
                        }
                    }
                    val message = StringBuilder()
                    if(list.isEmpty()){

                    } else if(list.size >= 2) {
                        list.joinTo(buffer = message, ",",""," is missing!")
                    } else {
                        message.append("${list[0]} is missing!")
                    }
                    scope.launch {
                        snackBarHostState.showSnackbar(message.toString())
                    }
                }
            ) { Text("Create New User") }
        },
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                NewUserContent()
            }
        }
    )
}

@Composable
private fun BasicPersonalInformation() {
    HeadLine(headline = "PersonalInformation")

}

@Composable
private fun SimpleDivider() {
    Divider(modifier = Modifier.padding(top = 20.dp, bottom = 20.dp))
}



@Composable
fun NewUserContent() {

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
                SimpleStateOutlinedTextField(Modifier, PersonDataValueType.NAME)
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
    valueTyp: PersonDataValueType) {
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
                SimpleStateDatePicker(modifier = Modifier, valueTyp = PersonDataValueType.DATE)
            }
        )
    }
}


@Composable
fun SimpleStateDatePicker(
    modifier: Modifier,
    valueTyp: PersonDataValueType) {
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
            Selection("Male", R.drawable.baseline_male_24),
            Selection("Female", R.drawable.baseline_female_24)
        ),
        rows = 1)
}
