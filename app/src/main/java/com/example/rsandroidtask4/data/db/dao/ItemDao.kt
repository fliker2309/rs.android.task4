package com.example.rsandroidtask4.data.db.dao

import androidx.room.*
import com.example.rsandroidtask4.data.db.entity.Employee
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    @Query("SELECT * FROM employees")
    fun getEmployees(): Flow<List<Employee>>

    @Query("SELECT * FROM employees WHERE id=(:id)")
    fun getEmployee(id: Int): Flow<Employee?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEmployee(employee: Employee)

    @Update
    fun updateEmployee(employee: Employee)

    @Delete
    suspend fun deleteEmployee(employee: Employee)

    @Query("SELECT * FROM employees ORDER BY age ASC")
    fun sortEmployeesByAge(): Flow<List<Employee>>

    @Query("SELECT * FROM employees ORDER BY name ASC")
    fun sortEmployeesByName(): Flow<List<Employee>>

    @Query("SELECT * FROM employees ORDER BY surname ASC")
    fun sortEmployeeBySurname(): Flow<List<Employee>>

    @Query("SELECT * FROM employees ORDER BY position ASC")
    fun sortEmployeesByPosition(): Flow<List<Employee>>

    @Query("SELECT * FROM employees ORDER BY `experience` ASC")
    fun sortEmployeesByExperience(): Flow<List<Employee>>

}