package com.example.hospitalinfrastructuremanagement.databases.interface


@Dao
interface DepartmentDao {
    @Insert
    suspend fun insert(department: Department)
    @Query("SELECT * FROM department")
    suspend fun getAll() : Flow<List<Department>>

    @Delete
    suspend fun delete(department: Department): Int

}