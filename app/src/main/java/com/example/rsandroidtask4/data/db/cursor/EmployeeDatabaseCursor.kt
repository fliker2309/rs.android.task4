package com.example.rsandroidtask4.data.db.cursor

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import com.example.rsandroidtask4.data.db.entity.Employee
import kotlinx.coroutines.flow.Flow
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
    context,
    DATABASE_NAME,
    null,
    DATABASE_VERSION
) {
    private val database get() = requireNotNull(INSTANCE)

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

    suspend fun insertEmployee(employee: Employee) {
        Log.d(TAG, "Cursor insertEmployee($employee)")

        val values = ContentValues()
        values.put("id", employee.id)
        values.put("name", employee.name)
        values.put("surname", employee.surname)
        values.put("age", employee.age)
        values.put("position", employee.position)
        values.put("experience", employee.experience)
        database.writableDatabase.insert(TABLE_NAME, null, values)
    }

    suspend fun updateEmployee(employee: Employee) {
        Log.d(TAG, "Cursor updateEmployee($employee)")
        val values = ContentValues()
        values.put("id", employee.id)
        values.put("name", employee.name)
        values.put("surname", employee.surname)
        values.put("age", employee.age)
        values.put("position", employee.position)
        values.put("experience", employee.experience)
        database.writableDatabase.update(
            TABLE_NAME,
            values,
            "id" + "=?",
            arrayOf(employee.id.toString())
        )
    }

    suspend fun deleteEmployee(employee: Employee) {
        Log.d(TAG, "Cursor deleteEmployee($employee)")
        database.writableDatabase.delete(TABLE_NAME, "id" + "=?", arrayOf(employee.id.toString()))
    }


    @SuppressLint("Range")
     fun getEmployeeList(): Flow<List<Employee>> {

        val listOfEmployees = mutableListOf<Employee>()
        val liveDataEmployees = MutableLiveData<List<Employee>>()
        val db = readableDatabase
        val selectQuery = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(selectQuery, null)
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
        Log.d(TAG, "Get all fun getEmployeeList")
        liveDataEmployees.value = listOfEmployees
        return liveDataEmployees.asFlow()
    }

    companion object{

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
