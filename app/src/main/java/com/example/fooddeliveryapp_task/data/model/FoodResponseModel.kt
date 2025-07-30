package com.example.fooddeliveryapp_task.data.model


import kotlinx.serialization.Serializable

@Serializable
data class FoodResponseModel(
    val categories: List<CategoryResponse>,
    val recommended: List<RecommendedItemResponse>,
    val sale: List<SaleItemResponse>
)

