package com.example.hospitalinfrastructuremanagement.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "Department",
)
data class Department(
    @PrimaryKey
    @ColumnInfo(name = "Department_ID") val departmentID: Int,
    @ColumnInfo(name = "Department_name") val departmentName: String
)
