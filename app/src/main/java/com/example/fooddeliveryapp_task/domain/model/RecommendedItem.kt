package com.example.fooddeliveryapp_task.domain.model

import com.example.fooddeliveryapp_task.utils.randomBg
import com.example.fooddeliveryapp_task.utils.randomImage

// Domain model for Recommended Item
data class RecommendedItem(
    val id: Int,
    val title: String,
    val subtitle: String,
    val imageName: Int = randomImage(),
    val rating: Double,
    val tags: List<String>,
    val deliveryTime: String,
    val backgroundColor: androidx.compose.ui.graphics.Color = randomBg()

)