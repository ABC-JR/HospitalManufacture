package com.example.hospitalinfrastructuremanagement.room

import com.example.hospitalinfrastructuremanagement.room.entities.AccidentEmergency
import com.example.hospitalinfrastructuremanagement.room.entities.Appointment
import com.example.hospitalinfrastructuremanagement.room.entities.Cabinet
import com.example.hospitalinfrastructuremanagement.room.entities.Department
import com.example.hospitalinfrastructuremanagement.room.entities.DepartmentChief
import com.example.hospitalinfrastructuremanagement.room.entities.Doctor
import com.example.hospitalinfrastructuremanagement.room.entities.Nurse
import com.example.hospitalinfrastructuremanagement.room.entities.Paediatrics
import com.example.hospitalinfrastructuremanagement.room.entities.Patient
import com.example.hospitalinfrastructuremanagement.room.entities.Room
import com.example.hospitalinfrastructuremanagement.room.entities.Staff
import com.example.hospitalinfrastructuremanagement.room.entities.Surgery
import com.example.hospitalinfrastructuremanagement.room.entities.Ward

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        AccidentEmergency::class,
        Appointment::class,
        Cabinet::class,
        Department::class,
        DepartmentChief::class,
        Doctor::class,
        Nurse::class,
        Paediatrics::class,
        Patient::class,
        Room::class,
        Staff::class,
        Surgery::class,
        Ward::class
    ],
    version = 1
)
abstract class HospitalDatabase: RoomDatabase() {
    abstract fun hospitalDao() : HospitalDao
}