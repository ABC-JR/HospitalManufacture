package com.example.hospitalinfrastructuremanagement.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DepartmentChief(
    @PrimaryKey
    @ColumnInfo(name = "ID") val staffID: Int,
    @ColumnInfo(name = "First_name") val firstName: String,
    @ColumnInfo(name = "Last_name") val lastName: String,
    @ColumnInfo(name = "Salary") val salary: Double,
    @ColumnInfo(name = "Hiring_date") val hiringDate: String,
    @ColumnInfo(name = "Department_ID") val departmentID: Int, // TODO: Foreign
)
