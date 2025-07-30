package com.example.fooddeliveryapp_task.data.mapper

import com.example.fooddeliveryapp_task.R
import com.example.fooddeliveryapp_task.data.model.CategoryResponse
import com.example.fooddeliveryapp_task.data.model.RecommendedItemResponse
import com.example.fooddeliveryapp_task.data.model.SaleItemResponse
import com.example.fooddeliveryapp_task.domain.model.Category
import com.example.fooddeliveryapp_task.domain.model.RecommendedItem
import com.example.fooddeliveryapp_task.domain.model.SaleItem

fun CategoryResponse.toDomain(): Category {
    return Category(
        title = this.title,
         // R.drawable.ic_burger
    )
}

fun RecommendedItemResponse.toDomain(): RecommendedItem {
    return RecommendedItem(
        id = this.id,
        title = this.title,
        subtitle = this.subtitle,
     //  imageName = this.image,
        rating = this.rating,
        tags = this.tags,
        deliveryTime = this.time
    )
}

fun SaleItemResponse.toDomain(): SaleItem {
    return SaleItem(
        id = this.id,
        title = this.title,
        subtitle = this.subtitle,
       // imageName = this.image,
        rating = this.rating,
        discountPercent = this.discountPercent
    )
}
