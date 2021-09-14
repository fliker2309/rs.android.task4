package com.example.rsandroidtask4.utils

const val TAG = "myLog"
const val DATABASE = "database"
const val SORT = "sort"
const val DATABASE_NAME = "employees_database"
const val TABLE_NAME = "employees"
const val ID_COLUMN = "id"
const val NAME_COLUMN = "name"
const val SURNAME_COLUMN = "surname"
const val AGE_COLUMN = "age"
const val POSITION_COLUMN = "position"
const val EXPERIENCE_COLUMN = "experience"
const val DATABASE_VERSION = 1
const val CREATE_TABLE_SQL =
    "CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
            "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            "name TEXT NOT NULL," +
            "surname TEXT NOT NULL" +
            "age TEXT NOT NULL" +
            "position TEXT NOT NULL" +
            "experience TEXT NOT NULL" +
            ");"
