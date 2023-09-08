package com.bluetiger.foodbrocompose.feature_user.ui.add_edit_user

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
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
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
import kotlinx.coroutines.launch




@Composable
fun AddEditUserScreenPersonal(
    viewModel: AddEditUserViewModel = hiltViewModel()
) {
    var state by remember { mutableIntStateOf(0) }
    val titles = listOf("Tab 1", "Tab 2")
    Column {
        TabRow(selectedTabIndex = state) {
            titles.forEachIndexed { index, title ->
                Tab(
                    selected = state == index,
                    onClick = { state = index },
                    text = { Text(text = title, maxLines = 2, overflow = TextOverflow.Ellipsis) }
                )
            }
        }
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "Text tab ${state + 1} selected",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}


@Composable
fun PersonalInformation(viewModel : AddEditUserViewModel){

}