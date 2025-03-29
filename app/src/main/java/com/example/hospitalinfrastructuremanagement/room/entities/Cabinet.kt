package com.example.hospitalinfrastructuremanagement.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "Cabinet",
    foreignKeys = [
        ForeignKey(
            entity = Room::class,
            parentColumns = arrayOf("Room_number"),
            childColumns = arrayOf("Room_number"),
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Staff::class,
            parentColumns = arrayOf("ID"),
            childColumns = arrayOf("Staff_ID"),
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Cabinet(
    @PrimaryKey
    @ColumnInfo(name = "Room_number") val roomNumber: Int,
    @ColumnInfo(name = "Staff_ID") val staffID: Int //
)
