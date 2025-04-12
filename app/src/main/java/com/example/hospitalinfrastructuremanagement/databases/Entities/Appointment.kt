package com.example.hospitalinfrastructuremanagement.databases.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "appoinment")
data class Appointment (
    @PrimaryKey(true)
    val id:Long= 0 ,
    val roomnmber:String ,
    val date:String ,
    val lastName: String ,
    val firstName:String ,
    val age:String ,
    val emergencyLevel:String ,
    val type:String ,

)