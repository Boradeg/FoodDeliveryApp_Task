package com.example.fooddeliveryapp_task.data.repository


import com.example.fooddeliveryapp_task.data.mapper.toDomain
import com.example.fooddeliveryapp_task.data.model.FoodResponseModel
import com.example.fooddeliveryapp_task.data.remote.ApiService
import com.example.fooddeliveryapp_task.domain.model.FoodScreenData
import com.example.fooddeliveryapp_task.domain.repository.FoodRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FoodRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : FoodRepository {
    override fun getFoodScreenData(): Flow<Result<FoodScreenData>> = flow {

        try {
            val response: FoodResponseModel = apiService.getFoodData()

            val foodScreenData = FoodScreenData(
                categories = response.categories.map { it.toDomain() },
                recommendedItems = response.recommended.map { it.toDomain() },
                saleItems = response.sale.map { it.toDomain() }
            )
            emit(Result.success(foodScreenData))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }.flowOn(Dispatchers.IO) // << Apply flowOn here
}
