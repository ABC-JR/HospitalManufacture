package com.example.hospitalinfrastructuremanagement.databases.interfacedao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.hospitalinfrastructuremanagement.databases.Entities.Department
import kotlinx.coroutines.flow.Flow


@Dao
interface DepartmentDao {
    @Insert
    suspend fun insert(department: Department)
    @Query("SELECT * FROM department")
    fun getAll() : Flow<List<Department>>

    @Delete
    suspend fun delete(department: Department): Int

}