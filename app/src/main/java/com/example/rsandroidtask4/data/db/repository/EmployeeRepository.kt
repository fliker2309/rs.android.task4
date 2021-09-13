package com.example.rsandroidtask4.data.db.repository

import androidx.lifecycle.LiveData
import com.example.rsandroidtask4.data.db.cursor.EmployeeDatabaseCursor
import com.example.rsandroidtask4.data.db.dao.EmployeeDao
import com.example.rsandroidtask4.data.db.entity.Employee

class EmployeeRepository(
    private val employeeDao: EmployeeDao,
    private val sqLiteDatabase: EmployeeDatabaseCursor
) {

    fun getEmployees(database: Int): LiveData<List<Employee>> {
        return when (database) {
            0 -> employeeDao.roomGetEmployees()
            else -> sqLiteDatabase.getEmployeeList()
        }
    }

    suspend fun insertEmployee(employee: Employee, database: Int) {
        when (database) {
            0 -> employeeDao.roomInsertEmployee(employee)
            1 -> sqLiteDatabase.insertEmployee(employee)
        }
    }

    suspend fun updateEmployee(employee: Employee, database: Int) {
        when (database) {
            0 -> employeeDao.roomUpdateEmployee(employee)
            1 -> sqLiteDatabase.updateEmployee(employee)
        }
    }

    suspend fun deleteEmployee(employee: Employee, database: Int) {
        when (database) {
            0 -> employeeDao.roomDeleteEmployee(employee)
            1 -> sqLiteDatabase.deleteEmployee(employee)
        }
    }

}