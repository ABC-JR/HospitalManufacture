package com.example.hospitalinfrastructuremanagement.databases.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "department")
data class Department(
    @PrimaryKey(autoGenerate = true)
    val deparmentid:Long =0 ,
    val departmentname :String
)