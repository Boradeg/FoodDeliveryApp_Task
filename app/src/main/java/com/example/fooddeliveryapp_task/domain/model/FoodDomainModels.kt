package com.example.fooddeliveryapp_task.domain.model

import com.example.fooddeliveryapp_task.utils.randomBg
import com.example.fooddeliveryapp_task.utils.randomImage


data class Category(
    val title: String,
    val imageResId: Int = randomImage(),
    val backgroundColor: androidx.compose.ui.graphics.Color = randomBg()
)




