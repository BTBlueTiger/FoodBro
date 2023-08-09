package com.bluetiger.foodbrocompose.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.bluetiger.foodbrocompose.Graph
import com.bluetiger.foodbrocompose.database.user.User
import com.bluetiger.foodbrocompose.ui.common.headline.HeadLine
import com.bluetiger.foodbrocompose.ui.common.stateable.StateAble
import com.bluetiger.foodbrocompose.ui.screens.new_user.NewUserContent
import kotlinx.coroutines.launch

private val model = HomeViewModel()
private val user = Graph.user

@Composable
fun Home() {
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
                    .padding(20.dp)
                    .fillMaxSize(),
                contentAlignment = Alignment.TopCenter
            ) {
                HomeScreen()
            }
        }
    )
}

@Composable
fun HomeScreen() {
    BasalMetabolic()
}

@Composable
fun SimpleBlackText(text: String){
    Text(text = text, style = TextStyle(color = Color.Black))
}

@Composable
fun BasalMetabolic() {
    val userState = user.collectAsState()

    Card(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.4f),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onTertiaryContainer)
    ) {
        Box(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxSize()
        )
        {
            Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {

                Row(Modifier.fillMaxWidth()) {
                    SimpleBlackText(text = "Calories per day: ${userState.value.getCaloriesPerDay()}")
                }
            }
        }
    }


}