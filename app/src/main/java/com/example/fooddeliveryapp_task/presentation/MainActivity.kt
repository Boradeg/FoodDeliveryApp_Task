package com.example.fooddeliveryapp_task.presentation


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.fooddeliveryapp_task.presentation.home.screens.MainScreen
import com.example.fooddeliveryapp_task.presentation.theme.FoodDeliveryApp_TaskTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodDeliveryApp_TaskTheme {
                MainScreen()
            }
        }
    }
}

