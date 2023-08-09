package com.bluetiger.foodbrocompose.ui.screens.user.create

import androidx.compose.runtime.Composable
import com.bluetiger.foodbrocompose.ui.screens.user.SimpleUserScreen

@Composable
fun CreateUserScreen() {
    SimpleUserScreen(
        floatingButtonText = "Create New User",
        onFloatingButtonClicked = {}
    )
}