package com.example.rsandroidtask4.data.db.repository

import com.example.rsandroidtask4.data.db.cursor.EmployeeDatabaseCursor
import com.example.rsandroidtask4.data.db.dao.EmployeeDao
import com.example.rsandroidtask4.data.db.database.EmployeeDatabase
import com.example.rsandroidtask4.data.db.entity.Employee
import kotlinx.coroutines.flow.Flow


class EmployeeRepository(private val employeeDao: EmployeeDao, private val sqLiteDatabase: EmployeeDatabaseCursor) {

   /* private val roomEmployeeDao: EmployeeDao = employeeDatabase.itemDao()*/

    val roomList : Flow<List<Employee>> = employeeDao.roomGetEmployees()
    val sqLiteList : Flow<List<Employee>> = sqLiteDatabase.getEmployeeList()
    //getByRoom репозиторий умный, сам решает откуда взять буль меньше места
    //GetByCursor

    //comments!
    fun roomGetEmployees(): Flow<List<Employee>> = roomEmployeeDao.roomGetEmployees()

    suspend fun roomInsertEmployee(employee: Employee) = roomEmployeeDao.roomInsertEmployee(employee)

    suspend fun roomUpdateEmployee(employee: Employee) = roomEmployeeDao.roomUpdateEmployee(employee)

    suspend fun roomDeleteEmployee(employee: Employee) = roomEmployeeDao.roomDeleteEmployee(employee)


}