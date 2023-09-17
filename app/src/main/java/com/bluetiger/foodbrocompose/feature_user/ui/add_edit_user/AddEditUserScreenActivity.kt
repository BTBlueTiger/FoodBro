package com.bluetiger.foodbrocompose.feature_user.ui.add_edit_user

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bluetiger.foodbrocompose.R
import com.bluetiger.foodbrocompose.feature_user.ui.add_edit_user.components.tabs.AddEditUserTab
import kotlinx.coroutines.launch


@Composable
fun AddEditUserScreenActivity(
    viewModel: AddEditUserScreenViewModel = hiltViewModel()
) {

    viewModel.onEvent(AddEditUserEvent.InitNewUser)

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    var selectedTab by remember { mutableIntStateOf(0) }

    val tabs = listOf(
        AddEditUserTab.PersonalInformation,
        AddEditUserTab.ActivityInformation,
        AddEditUserTab.FoodNutritionSettings
    )

    Scaffold(
        modifier = Modifier
            .padding(16.dp),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    scope.launch {

                    }
                },
            ) {
                Icon(
                    painterResource(id = R.drawable.baseline_arrow_circle_right_24),
                    contentDescription = ""
                )
            }
        },
    ) {
        Column(Modifier.padding(it)) {
            TabRow(selectedTabIndex = selectedTab) {
                tabs.forEachIndexed { index, tab ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = {
                                  selectedTab = index
                        },
                        text = { Text(tab.titleName) },
                        icon = {
                            Icon(
                                painterResource(id = tab.iconId),
                                contentDescription = ""
                            )
                        }
                    )
                }
            }
            tabs[selectedTab].TabRow()
        }
    }
}