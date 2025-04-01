package com.example.hospitalinfrastructuremanagement

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.Room
import com.example.hospitalinfrastructuremanagement.room.HospitalDatabase
import com.example.hospitalinfrastructuremanagement.screens.MainApp
import com.example.hospitalinfrastructuremanagement.ui.theme.HospitalInfrastructureManagementTheme

class MainActivity : ComponentActivity() {

    private val hospitalDb by lazy {
        Room.databaseBuilder(
            applicationContext,
            HospitalDatabase::class.java,
            "hospital.db"
        ).build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HospitalInfrastructureManagementTheme {
                MainApp()
            }
        }
    }
}

