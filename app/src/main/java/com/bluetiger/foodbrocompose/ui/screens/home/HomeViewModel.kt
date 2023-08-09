package com.bluetiger.foodbrocompose.ui.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bluetiger.foodbrocompose.Graph
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    init {
        viewModelScope.launch {
            Graph.userStore.getAllUser().collect{
                it.forEach {user ->
                    Log.e(user.email, user.toString())
                }
            }
        }
    }
}