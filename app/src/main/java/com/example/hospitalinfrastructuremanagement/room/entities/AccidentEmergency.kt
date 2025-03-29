package com.example.hospitalinfrastructuremanagement.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "Accident&Emergency",
    foreignKeys = [
        ForeignKey(
            entity = Patient::class,
            parentColumns = arrayOf("ID"),
            childColumns = arrayOf("Patient_ID"),
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Staff::class,
            parentColumns = arrayOf("ID"),
            childColumns = arrayOf("Staff_ID"),
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Ward::class,
            parentColumns = arrayOf("Room_number"),
            childColumns = arrayOf("Ward_number"),
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class AccidentEmergency(
    @ColumnInfo(name = "Patient_ID") val patientID: Int, //TODO: Foreign
    @ColumnInfo(name = "Staff_ID") val staffID: Int, //TODO: Foreign
    @ColumnInfo(name = "Ward_number") val wardNumber: Int, //TODO: Foreign
    @ColumnInfo(name = "Procedure_date/time") val procedureDateTime: String,
    @ColumnInfo(name = "Emergency_level") val emergencyLevel: Byte,
    @PrimaryKey(autoGenerate = true) val id: Int = 1
)