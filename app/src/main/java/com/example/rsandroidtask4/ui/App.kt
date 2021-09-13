package com.example.rsandroidtask4.ui

import android.app.Application
import com.example.rsandroidtask4.data.db.cursor.EmployeeDatabaseCursor
import com.example.rsandroidtask4.data.db.database.EmployeeDatabase
import com.example.rsandroidtask4.data.db.repository.EmployeeRepository

import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class App : Application() {

    private val employeeDatabase by lazy { EmployeeDatabase.getDatabase(this) }
    private val sqLiteDatabase by lazy { EmployeeDatabaseCursor.getSQLDatabase(this) }
    val repository by lazy { EmployeeRepository(employeeDatabase.itemDao(),sqLiteDatabase) }
}