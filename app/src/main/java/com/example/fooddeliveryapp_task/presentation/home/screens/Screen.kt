package com.example.fooddeliveryapp_task.presentation.home.screens

import androidx.annotation.DrawableRes
import com.example.fooddeliveryapp_task.R

// Replace with your actual routes
sealed class Screen(val route: String, val labelRes: Int, @DrawableRes val icon: Int) {
    object Home : Screen("home", R.string.home, R.drawable.ic_home)
    object Orders : Screen("orders", R.string.order, R.drawable.ic_order)
    object Profile : Screen("profile", R.string.profile, R.drawable.ic_user)
}