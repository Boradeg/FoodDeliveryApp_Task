package com.example.fooddeliveryapp_task.presentation.home.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fooddeliveryapp_task.presentation.BottomNavigationBar
import com.example.fooddeliveryapp_task.presentation.home.component.HomeTopBar
import com.example.fooddeliveryapp_task.presentation.bottomNavItems
import com.example.fooddeliveryapp_task.presentation.navigation.NavGraph

@Composable
fun HomeScreen() {
    val navController = rememberNavController()
    Scaffold(
        topBar = { HomeTopBar() },
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                items = bottomNavItems
            )

        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavGraph(navController = navController)
        }
    }
}
