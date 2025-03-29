package com.example.hospitalinfrastructuremanagement.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Patient(
    @PrimaryKey
    @ColumnInfo(name = "ID") val ID: Int,
    @ColumnInfo(name = "Age") val age: Byte,
    @ColumnInfo(name = "First_name") val firstName: String,
    @ColumnInfo(name = "Last_name") val lastName: String
)
