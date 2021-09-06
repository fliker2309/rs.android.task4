package com.example.rsandroidtask4.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.rsandroidtask4.data.db.entity.Employee
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    @Query("SELECT * FROM employees ORDER BY id ASC")
    fun getEmployees(): LiveData<List<Employee>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEmployee(employee: Employee)

    @Update
    suspend fun updateEmployee(employee: Employee)

    @Delete
    suspend fun deleteEmployee(employee: Employee)

    @Query("SELECT * FROM employees ORDER BY age ASC")
    fun sortEmployeesByAge(): LiveData<List<Employee>>

    @Query("SELECT * FROM employees ORDER BY name ASC")
    fun sortEmployeesByName(): LiveData<List<Employee>>

    @Query("SELECT * FROM employees ORDER BY surname ASC")
    fun sortEmployeeBySurname(): LiveData<List<Employee>>

    @Query("SELECT * FROM employees ORDER BY position ASC")
    fun sortEmployeesByPosition(): LiveData<List<Employee>>

    @Query("SELECT * FROM employees ORDER BY `experience` ASC")
    fun sortEmployeesByExperience(): LiveData<List<Employee>>
}