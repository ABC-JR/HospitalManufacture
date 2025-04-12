package com.example.hospitalinfrastructuremanagement.databases.interfacedao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.hospitalinfrastructuremanagement.databases.Entities.Appointment
import com.example.hospitalinfrastructuremanagement.databases.Entities.Nurse
import kotlinx.coroutines.flow.Flow


@Dao
interface NurseDao {
    @Insert
    suspend fun insert(nurse: Nurse)
    @Query("SELECT * FROM nurses")
    fun getAllNurses():Flow<List<Nurse>>
    @Delete
    suspend fun delete(nurse: Nurse)

    @Query("SELECT lastname FROM nurses")
    fun getAllNursesName():Flow<List<String>>


    @Query("SELECT COUNT(*) FROM nurses")
    fun getCountofNurses(): Flow<List<String>>


    @Query("SELECT SUM(CAST(salary AS INTEGER)) AS total_salary FROM nurses")
    fun getAllSallaryNurses() :Flow<Int>

    @Query("SELECT AVG(CAST(salary as String)) FROM nurses")
    fun getAverageSalaryNurses():Flow<List<String>>

    @Query("SELECT MIN(CAST(salary AS INTEGER)) FROM nurses")
    fun getMinSalaryNurses(): Flow<String>

    @Query("SELECT MAX(CAST(salary AS INTEGER)) FROM nurses")
    fun getMaxSalaryNurses(): Flow<String>


    @Query("DELETE FROM nurses WHERE lastname = :lastname AND firstname = :firstname")
     suspend fun deletenursesbynames(lastname:String ,firstname:String)













    @Query("""
    SELECT * FROM appoinment 
    WHERE CAST(emergencyLevel AS INTEGER) = (
        SELECT MAX(CAST(emergencyLevel AS INTEGER)) FROM appoinment
    )
""")
    fun getMostEmergencyAppointments(): Flow<List<Appointment>>



}