package com.example.hospitalinfrastructuremanagement

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.hospitalinfrastructuremanagement.room.HospitalDatabase
import com.example.hospitalinfrastructuremanagement.room.entities.Department
import com.example.hospitalinfrastructuremanagement.ui.theme.HospitalInfrastructureManagementTheme
import com.example.hospitalinfrastructuremanagement.viewmodels.appviewmodel.AppViewModel
import com.example.hospitalinfrastructuremanagement.viewmodels.departmentchiefviewmodel.DepartmentChiefEvent
import com.example.hospitalinfrastructuremanagement.viewmodels.departmentchiefviewmodel.DepartmentChiefViewModel
import com.example.hospitalinfrastructuremanagement.viewmodels.doctorviewmodel.DoctorViewModel
import com.example.hospitalinfrastructuremanagement.viewmodels.nurseviewmodel.NurseViewModel
import kotlinx.coroutines.launch
import com.example.hospitalinfrastructuremanagement.screens.MainApp as MainApp

class MainActivity : ComponentActivity() {

    private val hospitalDb by lazy {
        Room.databaseBuilder(
            applicationContext,
            HospitalDatabase::class.java,
            "hospital.db"
        ).build()
    }

    private val departmentChiefViewModel by viewModels<DepartmentChiefViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T: ViewModel> create(modelClass: Class<T>): T {
                    return DepartmentChiefViewModel(hospitalDb.hospitalDao()) as T
                }
            }
        }
    )

    private val doctorViewModel by viewModels<DoctorViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T: ViewModel> create(modelClass: Class<T>): T {
                    return DoctorViewModel(hospitalDb.hospitalDao()) as T
                }
            }
        }
    )

    private val nurseViewModel by viewModels<NurseViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T: ViewModel> create(modelClass: Class<T>): T {
                    return NurseViewModel(hospitalDb.hospitalDao()) as T
                }
            }
        }
    )

    private val appViewModel by viewModels<AppViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T: ViewModel> create(modelClass: Class<T>): T {
                    return AppViewModel(hospitalDb.hospitalDao()) as T
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val dao = hospitalDb.hospitalDao()
            lifecycleScope.launchWhenStarted {
                dao.upsertDepartment(Department(departmentID = 123, departmentName = "First department"))
                dao.upsertDepartment(Department(departmentID = 456, departmentName = "Second department"))
                dao.upsertDepartment(Department(departmentID = 789, departmentName = "Third department"))
            }
            departmentChiefViewModel.onEvent(DepartmentChiefEvent.SetStaffSalary(3300.0))
            departmentChiefViewModel.onEvent(DepartmentChiefEvent.SetStaffHiringDate("03.04.2025"))
            departmentChiefViewModel.onEvent(DepartmentChiefEvent.SetStaffDepartmentID(123))
            departmentChiefViewModel.onEvent(DepartmentChiefEvent.SetStaffFirstName("John"))
            departmentChiefViewModel.onEvent(DepartmentChiefEvent.SetAddingStaffLastName("Smith"))
            departmentChiefViewModel.onEvent(DepartmentChiefEvent.SetStaffID(1))
            departmentChiefViewModel.onEvent(DepartmentChiefEvent.SaveDepartmentChief)
            HospitalInfrastructureManagementTheme {
                MainApp(
                    departmentChiefViewModel,
                    doctorViewModel,
                    nurseViewModel,
                    appViewModel
                )
            }
        }
    }
}

