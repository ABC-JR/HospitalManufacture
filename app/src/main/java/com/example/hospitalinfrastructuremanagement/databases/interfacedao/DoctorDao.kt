package com.example.hospitalinfrastructuremanagement.databases.interfacedao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.hospitalinfrastructuremanagement.databases.Entities.Doctor
import kotlinx.coroutines.flow.Flow


@Dao
interface DoctorDao {


    @Insert
    suspend fun insert(doctor: Doctor)


    @Query("SELECT * FROM doctors")
    fun getAllDoctors():Flow<List<Doctor>>


    @Delete
    suspend fun delete(doctor: Doctor)


    @Query("SELECT lastname FROM doctors ")
    fun getAllLastName():Flow<List<String>>


    @Query("SELECT COUNT(*) FROM doctors")
    fun getAllDoctorsCount(): Flow<Long>



    @Query("SELECT SUM(CAST(salary AS INTEGER)) AS total_salary FROM doctors")
    fun getAllSallaryDoctors() :Flow<Int>






    @Query("SELECT AVG(CAST(salary as String)) FROM doctors")
    fun getAverageSalaryDoctor():Flow<List<String>>

    @Query("SELECT MIN(CAST(salary AS INTEGER)) FROM doctors")
    fun getMinSalaryDoctor(): Flow<String>

    @Query("SELECT MAX(CAST(salary AS INTEGER)) FROM doctors")
    fun getMaxSalaryDoctor(): Flow<String>


    @Query("DELETE FROM doctors WHERE lastname = :lastname AND firstname = :firstname")
    suspend fun deletedoctorsbynames(lastname:String ,firstname:String)


}