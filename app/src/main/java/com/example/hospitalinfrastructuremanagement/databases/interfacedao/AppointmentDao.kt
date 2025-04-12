package com.example.hospitalinfrastructuremanagement.databases.interfacedao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.hospitalinfrastructuremanagement.databases.Entities.Appointment
import kotlinx.coroutines.flow.Flow


@Dao
interface AppointmentDao {
    @Insert
    suspend fun insert(appointment: Appointment)

    @Delete
    suspend fun delete(appointment: Appointment)

    @Query("SELECT *  FROM appoinment")
    fun getAllAppointment():Flow<List<Appointment>>


    @Query("DELETE FROM appoinment WHERE firstName = :firstName AND lastName= :lastName AND emergencyLevel= :emergencyLevel")
    suspend fun deletebyName(firstName:String , lastName:String , emergencyLevel:String)

    @Update
    suspend fun updateAppointment(appointment: Appointment)
    @Query("""
    UPDATE appoinment 
    SET firstName = :firstName,
        lastname = :lastname,
        emergencyLevel = :emergencyLevel,
        roomnmber = :roomnmber,
        date = :date,
        age = :age,
        type = :type
    WHERE id = :id
""")
    suspend fun updatenotbyid(
        id: Long,
        firstName: String,
        lastname: String,
        emergencyLevel: String,
        roomnmber: String,
        date: String,
        age: String,
        type: String
    )

//
//
//    @Query("SELECT a.date, a.roomnmber, p.firstName, p.lastName FROM appoinment a JOIN Patient p ON a.id = p.id")
//    fun appointmentandinfo():Flow<List<String>>
//
//
//
//    @Query("SELECT * FROM appoinment WHERE id IN (SELECT id FROM Patient   WHERE CAST(age AS INTEGER) = (SELECT MAX(CAST(age AS INTEGER)) FROM Patient))")
//    fun getAppoitnmentageishigh(): Flow<List<Appointment>>



}