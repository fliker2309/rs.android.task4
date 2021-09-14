package com.example.rsandroidtask4.data.db.cursor

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rsandroidtask4.data.db.entity.Employee
import com.example.rsandroidtask4.utils.*
import java.sql.SQLException

private const val TAG = "SQLiteOpenHelper"

class EmployeeDatabaseCursor(context: Context) : SQLiteOpenHelper(
    context,
    DATABASE_NAME,
    null,
    DATABASE_VERSION
) {

    override fun onCreate(db: SQLiteDatabase?) {
        try {
            Log.d(TAG, "Creating database by cursor")
            db?.execSQL(CREATE_TABLE_SQL)
        } catch (exception: SQLException) {
            Log.e(TAG, "SQLiteOpenHelper", exception)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        Log.d(TAG, "onUpgrade")
    }

     fun insertEmployee(employee: Employee) {
         Log.d(TAG, "Cursor insertEmployee($employee)")
         val db = writableDatabase
         val values = ContentValues().apply {
             put(NAME_COLUMN, employee.name)
             put(SURNAME_COLUMN, employee.surname)
             put(AGE_COLUMN, employee.age)
             put(POSITION_COLUMN, employee.position)
             put(EXPERIENCE_COLUMN, employee.experience)
         }
         db.insert(TABLE_NAME, null, values)
         db.close()
     }

     fun updateEmployee(employee: Employee) {
         Log.d(TAG, "Cursor updateEmployee($employee)")
         val db = writableDatabase
         val values = ContentValues().apply {
             put(ID_COLUMN, employee.id)
             put(NAME_COLUMN, employee.name)
             put(SURNAME_COLUMN, employee.surname)
             put(AGE_COLUMN, employee.age)
             put(POSITION_COLUMN, employee.position)
             put(EXPERIENCE_COLUMN, employee.experience)
         }
         db.update(TABLE_NAME, values, "$ID_COLUMN=?", arrayOf(employee.id.toString()))
         db.close()
     }

     fun deleteEmployee(employee: Employee) {
         Log.d(TAG, "Cursor deleteEmployee($employee)")
         val db = writableDatabase
         db.delete(TABLE_NAME, "$ID_COLUMN=?", arrayOf(employee.id.toString()))
     }


    @SuppressLint("Range")
     fun getEmployeeList(): LiveData<List<Employee>> {
        val listOfEmployees = mutableListOf<Employee>()
        val liveDataEmployees = MutableLiveData<List<Employee>>()
        val db = readableDatabase
        val selectQuery = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(selectQuery, null)
        try {
            cursor.use {
                it.apply {
                    if (cursor.moveToFirst()) {
                        do {
                            val id = cursor.getInt(cursor.getColumnIndex(ID_COLUMN))
                            val name = cursor.getString(cursor.getColumnIndex(NAME_COLUMN))
                            val surname = cursor.getString(cursor.getColumnIndex(SURNAME_COLUMN))
                            val age = cursor.getString(cursor.getColumnIndex(AGE_COLUMN))
                            val position = cursor.getString(cursor.getColumnIndex(POSITION_COLUMN))
                            val experience =
                                cursor.getString(cursor.getColumnIndex(EXPERIENCE_COLUMN))
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
            }

        } catch (exception: SQLException) {
            Log.e(TAG, "Cursor", exception)
        } finally {
            cursor.close()
        }
        Log.d(TAG, "Get all fun getEmployeeList")
        liveDataEmployees.value = listOfEmployees
        return liveDataEmployees
    }

    companion object {

        @Volatile
        private var INSTANCE: EmployeeDatabaseCursor? = null

        fun getSQLDatabase(context: Context): EmployeeDatabaseCursor {
            Log.d(TAG, "Get SQLDatabase")
            return INSTANCE ?: synchronized(this) {
                val instance = EmployeeDatabaseCursor(context)
                INSTANCE = instance
                instance
            }
        }
    }
}
