package com.example.rsandroidtask4.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.rsandroidtask4.data.db.entity.Employee

@Dao
interface EmployeeDao {

    @Query("SELECT * FROM employees ORDER BY id ASC")
    fun getEmployees(): LiveData<List<Employee>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEmployee(employee: Employee)

    @Update
    suspend fun updateEmployee(employee: Employee)

    @Delete
    suspend fun deleteEmployee(employee: Employee)

 /*   @Query(
        "SELECT * FROM employees ORDER BY " +
                "CASE WHEN :order = 'name' THEN name END," +
                "CASE WHEN :order = 'surname' THEN surname END," +
                "CASE WHEN :order = 'age' THEN age END," +
                "CASE WHEN :order = 'position' THEN position END," +
                "CASE WHEN :order = 'experience' THEN experience END"
    )
    fun sortEmployees(order: String): LiveData<List<Employee>>*/


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