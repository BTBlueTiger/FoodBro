package com.bluetiger.foodbrocompose.ui.screens.users_list

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Face
import androidx.compose.material.icons.twotone.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bluetiger.foodbrocompose.R
import com.bluetiger.foodbrocompose.database.user.PersonDataValueType
import com.bluetiger.foodbrocompose.database.user.User
import com.bluetiger.foodbrocompose.ui.common.headline.HeadLine


val model = UsersScreenViewModel()

@Composable
fun UsersScreen() {

    val listOfUser by model.users.collectAsState()


    LazyColumn(
        contentPadding = PaddingValues(5.dp),

        ) {
        items(listOfUser) { user ->
            UserListItem(user = user)
        }
    }
}

@Composable
fun UserListItem(user: User) {

    var showAcceptClickedUser by remember { mutableStateOf(false) }
    if (showAcceptClickedUser) {
        AlertDialog(
            onDismissRequest = { showAcceptClickedUser = false },
            title = { HeadLine("Change current User") },
            text = {
                Text(
                    text = "Do you want to change the Applications User?", style = TextStyle(
                        Color.White
                    )
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        showAcceptClickedUser = false
                    },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(text = "No")
                }
                Button(
                    onClick = {
                        model.onUserChangeRequest(user)
                        showAcceptClickedUser = false
                    },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(text = "Yes")
                }
            }
        )
    }

    Card(
        Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .height(50.dp)
            .clickable {
                showAcceptClickedUser = true
            },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(0.1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(Icons.TwoTone.Person, contentDescription = "")
            }
            Column(
                modifier = Modifier.weight(0.8f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = user.email, style = TextStyle(fontSize = 20.sp, color = Color.White))
            }
            Column(
                modifier = Modifier.weight(0.1f),
                horizontalAlignment = Alignment.Start
            ) {
                Icon(
                    painter = painterResource(
                        PersonDataValueType.GENDER.getGenderIcon(user.gender?: "")),
                    contentDescription = ""
                )
            }
        }
    }
}