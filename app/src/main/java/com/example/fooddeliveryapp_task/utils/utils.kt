package com.example.fooddeliveryapp_task.utils

import androidx.compose.ui.graphics.Color
import com.example.fooddeliveryapp_task.R
import java.text.NumberFormat
import java.util.Locale

val lightBackgroundColors = listOf(
    Color(0xFFEFFBF1), // Light mint
    Color(0xFFE3F2FD), // Light blue
    Color(0xFFEDE7F6), // Light purple
    Color(0xFFFFF3E0)  // Light orange
)
fun Double.toCurrencyString(locale: Locale = Locale("en", "IN")): String { // Changed default locale
    return NumberFormat.getCurrencyInstance(locale).format(this)
}
fun randomImage(): Int {
    val imageList = listOf(
        R.drawable.ic_burger,
        R.drawable.ic_fruit_salad,
        R.drawable.ic_banner_mage,
        R.drawable.ic_icecream,
        R.drawable.ic_juice,
    )
    return imageList.random()
}


fun randomBg() : Color {
    return lightBackgroundColors.random()
}