package com.example.rsandroidtask4.ui

import android.app.Application
import android.content.Context
import com.example.rsandroidtask4.data.db.cursor.EmployeeDatabaseCursor
import com.example.rsandroidtask4.data.db.database.EmployeeDatabase
import com.example.rsandroidtask4.data.db.repository.EmployeeRepository
import com.example.rsandroidtask4.data.locator.ServiceLocator
import com.example.rsandroidtask4.data.locator.ServiceLocator.locate
import kotlinx.coroutines.InternalCoroutinesApi

class App : Application() {

    @InternalCoroutinesApi
    private val employeeDatabase by lazy { EmployeeDatabase.getDatabase(this) }
    private val sqLiteDatabase by lazy { EmployeeDatabaseCursor.getSQLDatabase(this) }

    @InternalCoroutinesApi
    override fun onCreate() {
        super.onCreate()

        ServiceLocator.register<Context>(this)
        ServiceLocator.register(EmployeeDatabase.getDatabase(locate()))
       /* ServiceLocator.register(EmployeeRepository(locate()))*/
    }
}