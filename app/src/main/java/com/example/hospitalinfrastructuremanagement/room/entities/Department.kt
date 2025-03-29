package com.example.hospitalinfrastructuremanagement.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Department(
    @PrimaryKey
    @ColumnInfo(name = "Department_ID") val departmentID: Int,
    @ColumnInfo(name = "Department_name") val departmentName: String,
    @ColumnInfo(name = "Department_chief_ID") val departmentChiefID: Int //TODO: Foreign
)
