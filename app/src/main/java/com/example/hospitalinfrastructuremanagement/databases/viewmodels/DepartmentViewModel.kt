package com.example.hospitalinfrastructuremanagement.databases.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope // 👈 ОБЯЗАТЕЛЬНО этот импорт
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hospitalinfrastructuremanagement.databases.Entities.Appointment
import com.example.hospitalinfrastructuremanagement.databases.Entities.Department
import com.example.hospitalinfrastructuremanagement.databases.Entities.Doctor
import com.example.hospitalinfrastructuremanagement.databases.Entities.Nurse
import com.example.hospitalinfrastructuremanagement.databases.Entities.Patient
import com.example.hospitalinfrastructuremanagement.databases.abstracclass.Database
import com.example.hospitalinfrastructuremanagement.databases.interfacedao.DepartmentDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch // 👈 И этот тоже нужен
import javax.inject.Inject

@HiltViewModel
class DepartmentViewModel @Inject constructor(
    private val dao: Database ,

) : ViewModel() { // 👈 ОБЯЗАТЕЛЬНО наследуемся от ViewModel

    var allworkers = dao.db().getAll().stateIn(viewModelScope , SharingStarted.Lazily , emptyList())

    var allnurses = dao.daodoctor().getAllDoctors().stateIn(viewModelScope , SharingStarted.Lazily , emptyList())

    val alldoctor = dao.daonurse().getAllNurses().stateIn(viewModelScope , SharingStarted.Lazily , emptyList())


    val allappointment = dao.daoAppointment().getAllAppointment().stateIn(viewModelScope , SharingStarted.Lazily , emptyList())



    fun insertItem(department: Department) {
        viewModelScope.launch {
            dao.db().insert(department) // Пример использования DAO
        }
    }

    fun  insertNurse(nurse: Nurse){
        viewModelScope.launch {
            dao.daonurse().insert(nurse)
        }
    }
    fun deleteNurse(nurse: Nurse){
        viewModelScope.launch {
            dao.daonurse().delete(nurse)
        }
    }

    fun insertDoctor(doctor: Doctor){
        viewModelScope.launch {
            dao.daodoctor().insert(doctor)
        }
    }

    fun deleteDoctor(doctor: Doctor){
        viewModelScope.launch {
            dao.daodoctor().delete(doctor)
        }
    }


    fun insertAppointment(appointment: Appointment){
        viewModelScope.launch {
            dao.daoAppointment().insert(
                appointment
            )
        }
    }

    fun deleteAppointment(appointment: Appointment){
        viewModelScope.launch {
            dao.daoAppointment().delete(appointment)
        }
    }


    fun deletetaskbynamelast(firstName:String , lastName:String , emergencyLevel:String){
        viewModelScope.launch {
            dao.daoAppointment().deletebyName(
                firstName =  firstName,
                lastName = lastName ,
                emergencyLevel = emergencyLevel
            )
        }
    }


    fun deletebynamedoctors(firstName: String , lastName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.daodoctor().deletedoctorsbynames(lastName, firstName)
        }
    }

    fun deletebynamenurses(firstName: String , lastName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.daonurse().deletenursesbynames(lastName, firstName)
        }
    }

    fun updatenotbyid(firstName: String , lastName: String ,emergencyLevel: String , roomnumber:String , date:String , age:String ,type:String , ){
        viewModelScope.launch (Dispatchers.IO){
            dao.daoAppointment().updatenotbyid(
                id = 1 ,
                firstName = firstName,
                emergencyLevel = emergencyLevel,
                roomnmber = roomnumber,
                date = date,
                age = age,
                type = type,
                lastname = lastName
            )
        }
    }
    fun updateAppointment(appointment: Appointment){
        viewModelScope.launch (Dispatchers.IO){
            dao.daoAppointment().updateAppointment(
                appointment
            )
        }
    }



    //____________________________________________________________________
    fun getMaxAgePatient(): Flow<String> = dao.daopatient().getmaxagepatient()
    fun getMinAgePatient(): Flow<String> = dao.daopatient().getminagepatient()
    fun getPatientWithoutAppointment(): Flow<List<Patient>> = dao.daopatient().getpatientnotappoitnment()

    fun getCountNurses(): Flow<List<String>> = dao.daonurse().getCountofNurses()
    fun getAllSalaryNurses(): Flow<Int> = dao.daonurse().getAllSallaryNurses()
    fun getAVGSalaryNurses(): Flow<List<String>> = dao.daonurse().getAverageSalaryNurses()
    fun getMinSalaryNurses(): Flow<String> = dao.daonurse().getMinSalaryNurses()
    fun getMaxSalaryNurses(): Flow<String> = dao.daonurse().getMaxSalaryNurses()

    fun getAllDoctorsCount(): Flow<Long> = dao.daodoctor().getAllDoctorsCount()
    fun getAllSalaryDoctors(): Flow<Int> = dao.daodoctor().getAllSallaryDoctors()
    fun getAVGSalaryDoctor(): Flow<List<String>> = dao.daodoctor().getAverageSalaryDoctor()
    fun getMaxSalaryDoctor(): Flow<String> = dao.daodoctor().getMaxSalaryDoctor()
    fun getMinSalaryDoctor(): Flow<String> = dao.daodoctor().getMinSalaryDoctor()













}
