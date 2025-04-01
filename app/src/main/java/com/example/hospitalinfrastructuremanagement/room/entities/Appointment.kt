package com.example.hospitalinfrastructuremanagement.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "Appointment",
    foreignKeys = [
        ForeignKey(
            entity = Staff::class,
            parentColumns = arrayOf("ID"),
            childColumns = arrayOf("Staff_ID"),
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Patient::class,
            parentColumns = arrayOf("ID"),
            childColumns = arrayOf("Patient_ID"),
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Cabinet::class,
            parentColumns = arrayOf("Room_number"),
            childColumns = arrayOf("Cabinet_number"),
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Appointment(
    @ColumnInfo(name = "Staff_ID") val staffID: Int, //
    @ColumnInfo(name = "Patient_ID") val patientID: Int, //
    @ColumnInfo(name = "Cabinet_number") val cabinetNumber: Int, //
    @ColumnInfo(name = "Appointment_date/time") val appointmentDateTime: String,
    @PrimaryKey(autoGenerate = true) val id: Int = 1
)
