package com.example.hospitalinfrastructuremanagement.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "Department_Chief",
    foreignKeys = [
        ForeignKey(
            entity = Staff::class,
            parentColumns = arrayOf("ID"),
            childColumns = arrayOf("ID"),
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Department::class,
            parentColumns = arrayOf("Department_ID"),
            childColumns = arrayOf("Department_ID"),
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class DepartmentChief(
    @PrimaryKey
    @ColumnInfo(name = "ID") val staffID: Int,
    @ColumnInfo(name = "Hiring_date") val hiringDate: String,
    @ColumnInfo(name = "First_name") val firstName: String,
    @ColumnInfo(name = "Last_name") val lastName: String,
    @ColumnInfo(name = "Salary") val salary: Double,
    @ColumnInfo(name = "Department_ID") val departmentID: Int, //
)
