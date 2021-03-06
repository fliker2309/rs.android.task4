package com.example.rsandroidtask4.data.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.rsandroidtask4.data.db.dao.EmployeeDao
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized
import com.example.rsandroidtask4.data.db.entity.Employee

@Database(entities = [Employee::class], version = 3, exportSchema = false)

abstract class EmployeeDatabase : RoomDatabase() {

    abstract fun itemDao(): EmployeeDao

    companion object {
        @Volatile
        private var INSTANCE: EmployeeDatabase? = null

        @InternalCoroutinesApi
        fun getDatabase(context: Context): EmployeeDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) return tempInstance

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EmployeeDatabase::class.java,
                    "employees_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}