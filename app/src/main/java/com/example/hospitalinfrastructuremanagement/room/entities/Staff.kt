package com.example.hospitalinfrastructuremanagement.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "Staff"
)
data class Staff(
    @PrimaryKey
    @ColumnInfo(name = "ID") val ID: Int,
    @ColumnInfo(name = "Hiring_date") val hiringDate: String,
    @ColumnInfo(name = "Hierarchy") val hierarchy: String
)
