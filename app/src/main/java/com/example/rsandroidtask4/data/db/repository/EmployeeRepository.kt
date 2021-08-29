package com.example.rsandroidtask4.data.db.repository

import com.example.rsandroidtask4.data.db.database.EmployeeDatabase
import com.example.rsandroidtask4.data.db.entity.Employee
import kotlinx.coroutines.flow.Flow


class EmployeeRepository(private val employeeDatabase: EmployeeDatabase) {

    private val employeeDao get() = employeeDatabase.itemDao()

    fun getEmployees(): Flow<List<Employee>> = employeeDao.getEmployees()
    fun getEmployee(id: Int): Flow<Employee?> = employeeDao.getEmployee(id)

    fun sortEmployeesByAge(): Flow<List<Employee>> = employeeDao.sortEmployeesByAge()
    fun sortEmployeesByName(): Flow<List<Employee>> = employeeDao.sortEmployeesByName()
    fun sortEmployeesByPosition(): Flow<List<Employee>> = employeeDao.sortEmployeesByPosition()
    fun sortEmployeesByExperience(): Flow<List<Employee>> = employeeDao.sortEmployeesByExperience()

    suspend fun insertEmployee(employee: Employee) = employeeDao.insertEmployee(employee)

    suspend fun updateEmployee(employee: Employee) = employeeDao.updateEmployee(employee)

    suspend fun deleteEmployee(employee: Employee) = employeeDao.deleteEmployee(employee)

    suspend fun wipeDatabase() = employeeDao.wipeDatabase()
}