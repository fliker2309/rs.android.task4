package com.example.rsandroidtask4.data.db.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.preference.ListPreference
import androidx.preference.PreferenceManager
import com.example.rsandroidtask4.data.db.dao.EmployeeDao
import com.example.rsandroidtask4.data.db.database.EmployeeDatabase
import com.example.rsandroidtask4.data.db.entity.Employee
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf


class EmployeeRepository(private val employeeDatabase: EmployeeDatabase) {

    private val employeeDao: EmployeeDao = employeeDatabase.itemDao()

    //getByRoom репозиторий умный, сам решает откуда взять буль меньше места
    //GetByCursor

    //comments!
    /* fun roomGetEmployees(*//*order: String*//*): LiveData<List<Employee>> = employeeDao.getEmployees(*//*order*//*)*/
    fun roomGetEmployees(): Flow<List<Employee>> = employeeDao.getEmployees()
   val employees = flowOf(listOf<Employee>())

    suspend fun roomInsertEmployee(employee: Employee) = employeeDao.insertEmployee(employee)

    suspend fun roomUpdateEmployee(employee: Employee) = employeeDao.updateEmployee(employee)

    suspend fun roomDeleteEmployee(employee: Employee) = employeeDao.deleteEmployee(employee)

}