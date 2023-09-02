package com.bluetiger.foodbrocompose.feature_user.ui.user_list

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@Composable
fun UserListScreen(
    viewModel: UserListViewModel = hiltViewModel()
) {

   val scope = rememberCoroutineScope()

    val users by viewModel.getUser().collectAsState(initial = emptyList())

    users.forEach { 
        Text(text = it.toString())
    }
}