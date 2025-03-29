package com.example.hospitalinfrastructuremanagement.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Cabinet(
    @PrimaryKey
    @ColumnInfo(name = "Room_number") val roomNumber: Int,
    @ColumnInfo(name = "Staff_ID") val staffID: Int //TODO: Foreign
)
