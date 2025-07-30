package com.example.fooddeliveryapp_task.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CategoryResponse(
    val title: String,
    val image: String
)