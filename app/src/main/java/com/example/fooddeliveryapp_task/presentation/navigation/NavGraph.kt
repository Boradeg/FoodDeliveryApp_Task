package com.example.fooddeliveryapp_task.presentation.navigation

// NavGraph.kt

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.fooddeliveryapp_task.presentation.home.screens.HomeScreen
import com.example.fooddeliveryapp_task.presentation.home.screens.OrderScreen
import com.example.fooddeliveryapp_task.presentation.home.viewmodel.HomeViewModel

@Composable
fun NavGraph(navController: NavHostController) {
    val homeViewModel: HomeViewModel = hiltViewModel() // Shared across all screens

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            val homeViewModel : HomeViewModel = hiltViewModel()
            HomeScreen(
                homeViewModel,
            )
        }

        composable(Screen.Orders.route) {
            OrderScreen(homeViewModel)
        }
        composable(Screen.Profile.route) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Profile Screen")
            }
        }
    }
}

