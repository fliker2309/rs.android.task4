package com.example.rsandroidtask4.data.db.repository

import com.example.rsandroidtask4.data.db.dao.EmployeeDao
import com.example.rsandroidtask4.data.db.database.EmployeeDatabase
import com.example.rsandroidtask4.data.db.entity.Employee
import kotlinx.coroutines.flow.Flow


class EmployeeRepository(private val employeeDatabase: EmployeeDatabase) {

    private val employeeDao: EmployeeDao = employeeDatabase.itemDao()

    //getByRoom репозиторий умный, сам решает откуда взять буль меньше места
    //GetByCursor

    //comments!
    fun roomGetEmployees(): Flow<List<Employee>> = employeeDao.roomGetEmployees()

    suspend fun roomInsertEmployee(employee: Employee) = employeeDao.roomInsertEmployee(employee)

    suspend fun roomUpdateEmployee(employee: Employee) = employeeDao.roomUpdateEmployee(employee)

    suspend fun roomDeleteEmployee(employee: Employee) = employeeDao.roomDeleteEmployee(employee)

    fun roomSortByName() : Flow<List<Employee>> = employeeDao.roomSortByName()
    fun roomSortBySurname() : Flow<List<Employee>> = employeeDao.roomSortBySurname()
    fun roomSortByAge() : Flow<List<Employee>> = employeeDao.roomSortByAge()
    fun roomSortByPosition() : Flow<List<Employee>> = employeeDao.roomSortByPosition()
    fun roomSortByExperience() : Flow<List<Employee>> = employeeDao.roomSortByExperience()
}