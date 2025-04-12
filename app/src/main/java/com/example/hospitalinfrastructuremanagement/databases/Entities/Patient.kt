package com.example.hospitalinfrastructuremanagement.databases.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Patient")
data class Patient (
    @PrimaryKey(true)
    val id:Long  =0 ,
    val lastName: String ,
    val firstName:String ,
    val age:String
)