package com.example.fooddeliveryapp_task.presentation.home.model

import com.example.fooddeliveryapp_task.domain.model.FoodScreenData

sealed interface HomeUiState {
    object Loading : HomeUiState
    data class Success(val foodScreenData: FoodScreenData) : HomeUiState
    data class Error(val message: String) : HomeUiState
}