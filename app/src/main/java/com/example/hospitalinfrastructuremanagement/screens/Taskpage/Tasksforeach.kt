package com.example.hospitalinfrastructuremanagement.screens.Taskpage

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.NavController


@Composable
fun Tasksforeach(paddingValues: PaddingValues, navController: NavController, name: String) {
    Box(contentAlignment = Alignment.Center){
        Text(text= name)
    }
}