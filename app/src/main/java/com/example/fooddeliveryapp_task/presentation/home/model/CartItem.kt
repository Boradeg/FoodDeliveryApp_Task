package com.example.fooddeliveryapp_task.presentation.home.model

import androidx.compose.runtime.Immutable

@Immutable
data class CartItem(
    val id: String,
    val name: String,
    val pricePerItem: Double,
    val imageResId: Int,
    val quantity: Int
) {
    val totalPrice: Double
        get() = pricePerItem * quantity
}