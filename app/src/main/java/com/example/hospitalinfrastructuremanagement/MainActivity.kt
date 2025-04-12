package com.example.hospitalinfrastructuremanagement

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.hospitalinfrastructuremanagement.databases.Entities.Department
import com.example.hospitalinfrastructuremanagement.databases.abstracclass.Database
import com.example.hospitalinfrastructuremanagement.databases.viewmodels.DepartmentViewModel

import com.example.hospitalinfrastructuremanagement.ui.theme.HospitalInfrastructureManagementTheme
import dagger.hilt.android.AndroidEntryPoint


import com.example.hospitalinfrastructuremanagement.screens.MainApp as MainApp



@AndroidEntryPoint
class MainActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val database = hiltViewModel<DepartmentViewModel>()

            HospitalInfrastructureManagementTheme {
                MainApp(
                    database
                )
            }
        }
    }
}

