package com.bluetiger.foodbrocompose.feature_user.ui.user_list

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bluetiger.foodbrocompose.database.FBPreferences
import com.bluetiger.foodbrocompose.main_activity.FoodBroActivityModel
import com.bluetiger.foodbrocompose.ui.common.components.headline.HeadLine
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListScreen(
    navigateToHome: () -> Unit,
    viewModel: UserListViewModel = hiltViewModel()
) {

    val users by viewModel.getUsers().collectAsState(initial = emptyList())

    users.forEach {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Card(
                modifier = Modifier
                    .padding(10.dp),
                onClick = {

                    viewModel.onEvent(UserListEvents.SetUser(it))
                    navigateToHome()
                },
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
                shape = CardDefaults.elevatedShape,
                elevation = CardDefaults.cardElevation(),
            ) {
                Spacer(modifier = Modifier.height(5.dp))
                HeadLine(headline = it.email)
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}