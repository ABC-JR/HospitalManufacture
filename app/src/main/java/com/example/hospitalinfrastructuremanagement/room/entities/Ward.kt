package com.example.hospitalinfrastructuremanagement.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Ward(
    @PrimaryKey
    @ColumnInfo(name = "Room_number") val roomNumber: Int,
    @ColumnInfo(name = "Type") val type: String,
    @ColumnInfo(name = "Doctor_ID") val doctorID: Int // TODO: Foreign
)
