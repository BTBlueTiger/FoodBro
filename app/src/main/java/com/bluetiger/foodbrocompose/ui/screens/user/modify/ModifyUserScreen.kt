package com.bluetiger.foodbrocompose.ui.screens.user.modify

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import com.bluetiger.foodbrocompose.Graph
import com.bluetiger.foodbrocompose.ui.screens.user.SimpleUserScreen

val user = Graph.user

@Composable
fun ModifyUserScreen() {

    val user = Graph.user.collectAsState()

    SimpleUserScreen(user = user.value, floatingButtonText = "Save",  onFloatingButtonClicked = {})
}