package com.example.fooddeliveryapp_task.presentation.home.component

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.fooddeliveryapp_task.R
import com.example.fooddeliveryapp_task.presentation.navigation.Screen




@Composable
fun BottomNavigationBar(navController: NavController, items: List<Screen>) {
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStack?.destination?.route

    NavigationBar(containerColor = Color.White) {
        items.forEach { item ->
            val selected = currentRoute == item.route

            NavigationBarItem(
                selected = selected,
                onClick = {
                    if (!selected) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = stringResource(id = item.labelRes),
                        modifier = Modifier.size(24.dp),
                        tint = if (selected) colorResource(R.color.green_500) else colorResource(
                            R.color.grey
                        )
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = item.labelRes),
                        color = if (selected) colorResource(R.color.green_500) else colorResource(
                            R.color.grey
                        )
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent,

                    )
            )
        }
    }
}
val bottomNavItems = listOf(
    Screen.Home,
    Screen.Orders,
    Screen.Profile
)
