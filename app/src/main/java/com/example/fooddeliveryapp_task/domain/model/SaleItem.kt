package com.example.fooddeliveryapp_task.domain.model

import com.example.fooddeliveryapp_task.utils.randomBg
import com.example.fooddeliveryapp_task.utils.randomImage

// Domain model for Sale Item
data class SaleItem(
    val id: Int,
    val title: String,
    val subtitle: String,
    val imageName: Int = randomImage(),
    val rating: Double,
    val discountPercent: Int,
    val time: String = "30 min",
    val backgroundColor: androidx.compose.ui.graphics.Color = randomBg()
)
