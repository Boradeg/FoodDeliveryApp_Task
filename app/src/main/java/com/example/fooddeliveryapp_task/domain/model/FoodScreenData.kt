package com.example.fooddeliveryapp_task.domain.model

// Data class to hold all structured domain data for the UI
data class FoodScreenData(
    val categories: List<Category>,
    val recommendedItems: List<RecommendedItem>,
    val saleItems: List<SaleItem>
)