package com.example.fooddeliveryapp_task.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SaleItemResponse(
    val id: Int,
    val title: String,
    val subtitle: String,
    val image: String,
    val rating: Double,
    @SerialName("discountPercent")
    val discountPercent: Int
)