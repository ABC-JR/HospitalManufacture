package com.example.hospitalinfrastructuremanagement.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "Room"
)
data class Room(
    @PrimaryKey
    @ColumnInfo(name = "Room_number") val roomNumber: Int
)
