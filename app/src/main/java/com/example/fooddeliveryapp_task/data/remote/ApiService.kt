package com.example.fooddeliveryapp_task.data.remote


import com.example.fooddeliveryapp_task.data.model.FoodResponseModel
import retrofit2.http.GET


interface ApiService {
    @GET("d713e0ed-58ea-4b3a-b9d7-23c5bf5dd50e")
    suspend fun getFoodData(): FoodResponseModel
}





