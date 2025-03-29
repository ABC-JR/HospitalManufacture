package com.example.hospitalinfrastructuremanagement.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Appointment(
    @ColumnInfo(name = "Staff_ID") val staffID: Int, //TODO: Foreign
    @ColumnInfo(name = "Patient_ID") val patientID: Int, //TODO: Foreign
    @ColumnInfo(name = "Cabinet_number") val cabinetNumber: Int, //TODO: Foreign
    @ColumnInfo(name = "Appointment_date/time") val appointmentDateTime: String,
    @PrimaryKey(autoGenerate = true) val id: Int = 1
)
