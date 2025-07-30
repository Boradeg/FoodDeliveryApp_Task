package com.example.fooddeliveryapp_task.data.model

import kotlinx.serialization.Serializable

@Serializable
data class RecommendedItemResponse(
    val id: Int,
    val title: String,
    val subtitle: String,
    val image: String,
    val rating: Double,
    val tags: List<String>,
    val time: String
)