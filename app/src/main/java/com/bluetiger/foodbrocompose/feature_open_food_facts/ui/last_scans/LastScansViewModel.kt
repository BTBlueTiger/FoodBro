package com.bluetiger.foodbrocompose.feature_open_food_facts.ui.last_scans

import androidx.lifecycle.ViewModel
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.repository.OpenFoodFactsRepository
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.use_case.OpenFoodFactDataUseCases
import com.bluetiger.foodbrocompose.feature_user.domain.repository.UserRepository
import com.bluetiger.foodbrocompose.feature_user.domain.use_case.UserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LastScansViewModel @Inject constructor(
    private val userUseCases: UserUseCases,
    private val foodFactDataUseCases: OpenFoodFactDataUseCases
) : ViewModel() {
    val scannedFoodFacts = foodFactDataUseCases.getOpenFoodFactsByUser(userUseCases.getCurrentUser()!!)
}