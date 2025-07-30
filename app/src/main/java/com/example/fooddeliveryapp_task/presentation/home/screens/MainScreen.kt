package com.example.fooddeliveryapp_task.presentation.home.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

import com.example.fooddeliveryapp_task.presentation.home.component.BottomNavigationBar
import com.example.fooddeliveryapp_task.presentation.home.component.HomeTopBar
import com.example.fooddeliveryapp_task.presentation.home.component.bottomNavItems
import com.example.fooddeliveryapp_task.presentation.navigation.NavGraph
import com.example.fooddeliveryapp_task.presentation.navigation.Screen

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val screensWithSearchBar = listOf(Screen.Home.route)
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            val showSearchBar = currentRoute in screensWithSearchBar
            HomeTopBar(isSearchBar = showSearchBar)
        },
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
