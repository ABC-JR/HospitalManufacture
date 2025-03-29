package com.example.hospitalinfrastructuremanagement.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Surgery(
    @ColumnInfo(name = "Patient_ID") val patientID: Int, //TODO: Foreign
    @ColumnInfo(name = "Staff_ID") val staffID: Int, //TODO: Foreign
    @ColumnInfo(name = "Ward_number") val wardNumber: Int, //TODO: Foreign
    @ColumnInfo(name = "Procedure_date/time") val procedureDateTime: String,
    @ColumnInfo(name = "Type") val type: String,
    @PrimaryKey(autoGenerate = true) val id: Int = 1
)
