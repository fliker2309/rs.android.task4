package com.example.rsandroidtask4.data.db.repository

import com.example.rsandroidtask4.data.db.dao.EmployeeDao
import com.example.rsandroidtask4.data.db.database.EmployeeDatabase
import com.example.rsandroidtask4.data.db.entity.Employee
import kotlinx.coroutines.flow.Flow


class EmployeeRepository(private val employeeDatabase: EmployeeDatabase) {

    private val roomEmployeeDao: EmployeeDao = employeeDatabase.itemDao()

    //getByRoom репозиторий умный, сам решает откуда взять буль меньше места
    //GetByCursor

    //comments!
    fun roomGetEmployees(): Flow<List<Employee>> = roomEmployeeDao.roomGetEmployees()

    suspend fun roomInsertEmployee(employee: Employee) = roomEmployeeDao.roomInsertEmployee(employee)

    suspend fun roomUpdateEmployee(employee: Employee) = roomEmployeeDao.roomUpdateEmployee(employee)

    suspend fun roomDeleteEmployee(employee: Employee) = roomEmployeeDao.roomDeleteEmployee(employee)

    fun roomSortByName() : Flow<List<Employee>> = roomEmployeeDao.roomSortByName()
    fun roomSortBySurname() : Flow<List<Employee>> = roomEmployeeDao.roomSortBySurname()
    fun roomSortByAge() : Flow<List<Employee>> = roomEmployeeDao.roomSortByAge()
    fun roomSortByPosition() : Flow<List<Employee>> = roomEmployeeDao.roomSortByPosition()
    fun roomSortByExperience() : Flow<List<Employee>> = roomEmployeeDao.roomSortByExperience()
}