package com.bluetiger.foodbrocompose.feature_open_food_facts.ui.last_scans

import androidx.lifecycle.ViewModel
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.repository.OpenFoodFactsRepository
import com.bluetiger.foodbrocompose.feature_user.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LastScansViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val foodFactsRepository: OpenFoodFactsRepository
) : ViewModel() {
    val scannedFoodFacts = foodFactsRepository.getOpenFoodFactsResponsesFromUser(userRepository.flowUser.value!!)
}