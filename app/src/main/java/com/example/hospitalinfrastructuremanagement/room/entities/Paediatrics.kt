package com.example.hospitalinfrastructuremanagement.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "Paediatrics",
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
data class Paediatrics(
    @ColumnInfo(name = "Patient_ID") val patientID: Int, //TODO: Foreign
    @ColumnInfo(name = "Staff_ID") val staffID: Int, //TODO: Foreign
    @ColumnInfo(name = "Ward_number") val wardNumber: Int, //TODO: Foreign
    @ColumnInfo(name = "Procedure_date/time") val procedureDateTime: String,
    @ColumnInfo(name = "Age Category") val ageCategory: String,
    @PrimaryKey(autoGenerate = true) val id: Int = 1
)