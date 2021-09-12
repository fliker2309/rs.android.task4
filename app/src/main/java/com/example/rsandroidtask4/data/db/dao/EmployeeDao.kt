package com.example.rsandroidtask4.data.db.dao

import androidx.room.*
import com.example.rsandroidtask4.data.db.entity.Employee
import kotlinx.coroutines.flow.Flow

@Dao
interface EmployeeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun roomInsertEmployee(employee: Employee)

    @Update
    suspend fun roomUpdateEmployee(employee: Employee)

    @Delete
    suspend fun roomDeleteEmployee(employee: Employee)

    @Query("SELECT * FROM employees")
    fun roomGetEmployees(): Flow<List<Employee>>

    @Query("SELECT * FROM employees ORDER BY name ASC")
    fun roomSortByName(): Flow<List<Employee>>

    @Query("SELECT * FROM employees ORDER BY surname ASC")
    fun roomSortBySurname(): Flow<List<Employee>>

    @Query("SELECT * FROM employees ORDER BY age ASC")
    fun roomSortByAge(): Flow<List<Employee>>

    @Query("SELECT * FROM employees ORDER BY position ASC")
    fun roomSortByPosition(): Flow<List<Employee>>

    @Query("SELECT * FROM employees ORDER BY experience ASC")
    fun roomSortByExperience(): Flow<List<Employee>>
}