package com.example.rsandroidtask4.data.db.repository

import com.example.rsandroidtask4.data.db.cursor.EmployeeDatabaseCursor
import com.example.rsandroidtask4.data.db.dao.EmployeeDao
import com.example.rsandroidtask4.data.db.entity.Employee
import com.example.rsandroidtask4.ui.settings.UsesDatabase
import kotlinx.coroutines.flow.Flow


class EmployeeRepository(
    private val employeeDao: EmployeeDao,
    private val sqLiteDatabase: EmployeeDatabaseCursor
) {

    val roomList: Flow<List<Employee>> = employeeDao.roomGetEmployees()
    val sqLiteList: Flow<List<Employee>> = sqLiteDatabase.getEmployeeList()

    fun getEmployees(database: UsesDatabase): Flow<List<Employee>> {
        return when (database.name) {
            UsesDatabase.ROOM.name -> employeeDao.roomGetEmployees()
            else -> sqLiteDatabase.getEmployeeList()
        }
    }

  suspend fun insertEmployee(employee: Employee, database: UsesDatabase) {
      when(database.name){
          UsesDatabase.ROOM.name ->  employeeDao.roomInsertEmployee(employee)
          UsesDatabase.CURSOR.name -> sqLiteDatabase.insertEmployee(employee)
      }
  }

    suspend fun updateEmployee(employee: Employee, database: UsesDatabase) {
        when(database.name){
            UsesDatabase.ROOM.name ->  employeeDao.roomUpdateEmployee(employee)
            UsesDatabase.CURSOR.name ->  sqLiteDatabase.updateEmployee(employee)
        }
    }

    suspend fun deleteEmployee(employee: Employee, database: UsesDatabase){
        when(database.name) {
            UsesDatabase.ROOM.name -> employeeDao.roomDeleteEmployee(employee)
            UsesDatabase.CURSOR.name -> sqLiteDatabase.deleteEmployee(employee)
        }
    }

}