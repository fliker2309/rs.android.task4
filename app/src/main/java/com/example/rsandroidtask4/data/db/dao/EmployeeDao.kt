package com.example.rsandroidtask4.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.rsandroidtask4.data.db.entity.Employee

@Dao
interface EmployeeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEmployee(employee: Employee)

    @Update
    suspend fun updateEmployee(employee: Employee)

    @Delete
    suspend fun deleteEmployee(employee: Employee)

    @Query(
        "SELECT * FROM employees ORDER BY " +
                "CASE WHEN :order = 'name' THEN name END," +
                "CASE WHEN :order = 'surname' THEN surname END," +
                "CASE WHEN :order = 'age' THEN age END," +
                "CASE WHEN :order = 'position' THEN position END," +
                "CASE WHEN :order = 'experience' THEN experience END"
    )
    fun getEmployees(order: String): LiveData<List<Employee>>

}