package com.example.rsandroidtask4.data.db.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.preference.ListPreference
import androidx.preference.PreferenceManager
import com.example.rsandroidtask4.data.db.dao.EmployeeDao
import com.example.rsandroidtask4.data.db.database.EmployeeDatabase
import com.example.rsandroidtask4.data.db.entity.Employee


class EmployeeRepository(private val employeeDatabase: EmployeeDatabase, val context:Context) {


    private var employeeDao : EmployeeDao = employeeDatabase.itemDao()
        get() {
            val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            val dbms = preferenceManager.findPreference<ListPreference>("dbms_")


            employeeDatabase.itemDao()}

    fun getEmployees(order: String): LiveData<List<Employee>> = employeeDao.getEmployees(order)

    suspend fun insertEmployee(employee: Employee) = employeeDao.insertEmployee(employee)

    suspend fun updateEmployee(employee: Employee) = employeeDao.updateEmployee(employee)

    suspend fun deleteEmployee(employee: Employee) = employeeDao.deleteEmployee(employee)

}