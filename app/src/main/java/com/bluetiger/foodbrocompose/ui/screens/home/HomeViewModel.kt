package com.bluetiger.foodbrocompose.ui.screens.home

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bluetiger.foodbrocompose.Graph
import com.bluetiger.foodbrocompose.database.FBPreferences
import com.bluetiger.foodbrocompose.database.user.User
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {


    init {
        viewModelScope.launch {
        }
    }
}