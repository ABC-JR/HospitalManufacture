package com.example.hospitalinfrastructuremanagement.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "Ward",
    foreignKeys = [
        ForeignKey(
            entity = Room::class,
            parentColumns = arrayOf("Room_number"),
            childColumns = arrayOf("Room_number"),
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Doctor::class,
            parentColumns = arrayOf("ID"),
            childColumns = arrayOf("Doctor_ID"),
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Ward(
    @PrimaryKey
    @ColumnInfo(name = "Room_number") val roomNumber: Int,
    @ColumnInfo(name = "Type") val type: String,
    @ColumnInfo(name = "Doctor_ID") val doctorID: Int //
)
