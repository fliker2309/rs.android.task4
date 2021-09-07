package com.example.rsandroidtask4.data.db.cursor

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.rsandroidtask4.data.db.dao.EmployeeDao
import com.example.rsandroidtask4.data.db.entity.Employee
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.sql.SQLException

private const val TAG = "SQLiteOpenHelper"
private const val DATABASE_NAME = "employees_database"
private const val TABLE_NAME = "employees"
private const val DATABASE_VERSION = 3
private const val CREATE_TABLE_SQL =
    "CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
            "id INTEGER NOT NULL," +
            "name TEXT NOT NULL," +
            "surname TEXT NOT NULL" +
            "age TEXT NOT NULL" +
            "position TEXT NOT NULL" +
            "experience TEXT NOT NULL" +
            "PRIMARY KEY (id AUTOINCREMENT)" +
            ");"


class EmployeeDatabaseCursor(context: Context) : SQLiteOpenHelper(
    context, DATABASE_NAME, null,
    DATABASE_VERSION
), EmployeeDao {
    override fun onCreate(db: SQLiteDatabase?) {
        try {
            db?.execSQL(CREATE_TABLE_SQL)
        } catch (exception: SQLException) {
            Log.e(TAG, "SQLiteOpenHelper", exception)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        Log.d(TAG, "onUpgrade")
    }

    override fun getEmployees(): LiveData<List<Employee>> {
        return liveData <List<Employee>> {
            emit(getEmployeeList())
        }
    }

    override suspend fun insertEmployee(employee: Employee) {
        Log.d(TAG, "Cursor insertEmployee($employee)")
        val db = writableDatabase
        val values = ContentValues()
        values.put("id", employee.id)
        values.put("name", employee.name)
        values.put("surname", employee.surname)
        values.put("age", employee.age)
        values.put("position", employee.position)
        values.put("experience", employee.experience)
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    override suspend fun updateEmployee(employee: Employee) {
        Log.d(TAG, "Cursor updateEmployee($employee)")
        val db = writableDatabase
        val values = ContentValues()
        values.put("id", employee.id)
        values.put("name", employee.name)
        values.put("surname", employee.surname)
        values.put("age", employee.age)
        values.put("position", employee.position)
        values.put("experience", employee.experience)
        db.update(TABLE_NAME, values, "id" + "=?", arrayOf(employee.id.toString()))
        db.close()
    }

    override suspend fun deleteEmployee(employee: Employee) {
        val db = writableDatabase
        Log.d(TAG, "Cursor deleteEmployee($employee)")
        db.delete(TABLE_NAME, "id" + "=?", arrayOf(employee.id.toString()))
        db.close()
    }

    override fun sortEmployeesByAge(): LiveData<List<Employee>> {
        TODO("Not yet implemented")
    }

    override fun sortEmployeesByName(): LiveData<List<Employee>> {
        TODO("Not yet implemented")
    }

    override fun sortEmployeeBySurname(): LiveData<List<Employee>> {
        TODO("Not yet implemented")
    }

    override fun sortEmployeesByPosition(): LiveData<List<Employee>> {
        TODO("Not yet implemented")
    }

    override fun sortEmployeesByExperience(): LiveData<List<Employee>> {
        TODO("Not yet implemented")
    }

    /* override fun sortEmployees(order: String): LiveData<List<Employee>> {
         TODO("Not yet implemented")
     }*/

    @SuppressLint("Range")
    private suspend fun getEmployeeList(): List<Employee> {
        return withContext(Dispatchers.IO) {
            val listOfEmployees = mutableListOf<Employee>()
            val db = readableDatabase
            val selectQuery = "SELECT * FROM $TABLE_NAME"
            val cursor = db.rawQuery(selectQuery,null)
            try {
                cursor.let {
                    if (cursor.moveToFirst()) {
                        do {
                            val id = cursor.getInt(cursor.getColumnIndex("id"))
                            val name = cursor.getString(cursor.getColumnIndex("name"))
                            val surname = cursor.getString(cursor.getColumnIndex("surname"))
                            val age = cursor.getString(cursor.getColumnIndex("age"))
                            val position = cursor.getString(cursor.getColumnIndex("position"))
                            val experience = cursor.getString(cursor.getColumnIndex("experience"))
                            listOfEmployees.add(
                                Employee(
                                    id,
                                    name,
                                    surname,
                                    age,
                                    position,
                                    experience
                                )
                            )
                        } while (cursor.moveToNext())
                    }
                }

            } catch (exception: SQLException) {
                Log.e(TAG, "Cursor", exception)
            } finally {
                cursor.close()
            }

            listOfEmployees
        }

    }


   /* private fun getCursorWithEmployees(): Cursor {
        return writableDatabase.rawQuery("SELECT * FROM $TABLE_NAME ", null)
    }*/

}