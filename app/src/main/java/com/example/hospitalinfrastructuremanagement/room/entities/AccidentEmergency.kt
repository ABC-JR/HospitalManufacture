package com.example.hospitalinfrastructuremanagement.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AccidentEmergency(
    @ColumnInfo(name = "Patient_ID") val patientID: Int, //TODO: Foreign
    @ColumnInfo(name = "Staff_ID") val staffID: Int, //TODO: Foreign
    @ColumnInfo(name = "Ward_number") val wardNumber: Int, //TODO: Foreign
    @ColumnInfo(name = "Procedure_date/time") val procedureDateTime: String,
    @ColumnInfo(name = "Emergency_level") val emergencyLevel: Byte,
    @PrimaryKey(autoGenerate = true) val id: Int = 1
)