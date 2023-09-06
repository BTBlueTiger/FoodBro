package com.bluetiger.foodbrocompose.feature_open_food_facts.ui.food_fact.row

import androidx.lifecycle.ViewModel
import com.bluetiger.foodbrocompose.feature_open_food_facts.domain.use_case.OpenFoodFactDataUseCases
import com.bluetiger.foodbrocompose.feature_user.domain.use_case.UserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OpenFoodFactsRowViewModel @Inject constructor(
    private val userUseCases: UserUseCases,
    private val foodFactDataUseCases: OpenFoodFactDataUseCases
) : ViewModel() {

}