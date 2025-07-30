package com.example.fooddeliveryapp_task.domain.repository

import com.example.fooddeliveryapp_task.domain.model.FoodScreenData
import kotlinx.coroutines.flow.Flow
interface FoodRepository {
    fun getFoodScreenData(): Flow<Result<FoodScreenData>>
}