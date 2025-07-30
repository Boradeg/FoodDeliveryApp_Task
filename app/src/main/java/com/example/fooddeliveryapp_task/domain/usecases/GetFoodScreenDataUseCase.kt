package com.example.fooddeliveryapp_task.domain.usecases


import com.example.fooddeliveryapp_task.domain.model.FoodScreenData
import com.example.fooddeliveryapp_task.domain.repository.FoodRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFoodScreenDataUseCase @Inject constructor(
    private val foodRepository: FoodRepository
) {
    operator fun invoke(): Flow<Result<FoodScreenData>> {
        return foodRepository.getFoodScreenData()
    }
}
