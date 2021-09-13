package com.example.rsandroidtask4.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.rsandroidtask4.data.db.entity.Employee

@Dao
interface EmployeeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun roomInsertEmployee(employee: Employee)

    @Update
    suspend fun roomUpdateEmployee(employee: Employee)

    @Delete
    suspend fun roomDeleteEmployee(employee: Employee)

    @Query("SELECT * FROM employees")
    fun roomGetEmployees(): LiveData<List<Employee>>

}