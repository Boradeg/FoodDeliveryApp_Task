package com.example.fooddeliveryapp_task.presentation.home.component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.fooddeliveryapp_task.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar() {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.home),
                style = MaterialTheme.typography.titleMedium
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
    )
}
