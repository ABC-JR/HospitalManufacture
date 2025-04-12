package com.example.hospitalinfrastructuremanagement.databases.abstracclass

import androidx.room.Dao
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hospitalinfrastructuremanagement.databases.Entities.Appointment
import com.example.hospitalinfrastructuremanagement.databases.Entities.Department
import com.example.hospitalinfrastructuremanagement.databases.Entities.Doctor
import com.example.hospitalinfrastructuremanagement.databases.Entities.Nurse
import com.example.hospitalinfrastructuremanagement.databases.Entities.Patient
import com.example.hospitalinfrastructuremanagement.databases.interfacedao.AppointmentDao
import com.example.hospitalinfrastructuremanagement.databases.interfacedao.DepartmentDao
import com.example.hospitalinfrastructuremanagement.databases.interfacedao.DoctorDao
import com.example.hospitalinfrastructuremanagement.databases.interfacedao.NurseDao
import com.example.hospitalinfrastructuremanagement.databases.interfacedao.PatientDao

@Database(entities = [Department::class, Nurse::class, Doctor::class  , Patient::class, Appointment::class] , version = 4 , exportSchema = true)
abstract class Database :RoomDatabase() {
    abstract fun db():DepartmentDao
    abstract fun daodoctor():DoctorDao
    abstract fun daonurse():NurseDao
    abstract fun daopatient() :PatientDao
    abstract fun daoAppointment():AppointmentDao
}