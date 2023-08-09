package com.bluetiger.foodbrocompose.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bluetiger.foodbrocompose.ui.common.headline.HeadLine
import com.bluetiger.foodbrocompose.ui.common.stateable.StateAble
import com.bluetiger.foodbrocompose.ui.screens.new_user.NewUserContent
import kotlinx.coroutines.launch

private val model = HomeViewModel()

@Composable
fun Home(){
    val snackBarHostState = SnackbarHostState()
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackBarHostState) },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                modifier = Modifier.padding(20.dp),
                onClick = {}
            ) { Text("") }
        },
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {

            }
        }
    )
}
