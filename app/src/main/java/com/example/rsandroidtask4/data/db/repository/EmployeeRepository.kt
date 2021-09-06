package com.example.rsandroidtask4.data.db.repository

import androidx.lifecycle.LiveData
import com.example.rsandroidtask4.data.db.database.EmployeeDatabase
import com.example.rsandroidtask4.data.db.entity.Employee
import kotlinx.coroutines.flow.Flow


class EmployeeRepository(private val employeeDatabase: EmployeeDatabase) {

    private val employeeDao get() = employeeDatabase.itemDao()

    fun getEmployees(): LiveData<List<Employee>> = employeeDao.getEmployees()

    fun sortEmployeesByAge():  LiveData<List<Employee>> = employeeDao.sortEmployeesByAge()
    fun sortEmployeesByName(): LiveData<List<Employee>> = employeeDao.sortEmployeesByName()
    fun sortEmployeesByPosition():  LiveData<List<Employee>> = employeeDao.sortEmployeesByPosition()
    fun sortEmployeesByExperience():  LiveData<List<Employee>> = employeeDao.sortEmployeesByExperience()
    fun sortEmployeesBySurname(): LiveData<List<Employee>> = employeeDao.sortEmployeeBySurname()

    suspend fun insertEmployee(employee: Employee) = employeeDao.insertEmployee(employee)

    suspend fun updateEmployee(employee: Employee) = employeeDao.updateEmployee(employee)

    suspend fun deleteEmployee(employee: Employee) = employeeDao.deleteEmployee(employee)
}