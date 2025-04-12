package com.example.hospitalinfrastructuremanagement.databases.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "nurses")
data class Nurse(
    @PrimaryKey(autoGenerate = true)
    val id:Long = 0 ,
    val hiringdate:String ,
    val salary:String ,
    val lastname:String ,
    val firstname:String ,
    val idforenter :String

)