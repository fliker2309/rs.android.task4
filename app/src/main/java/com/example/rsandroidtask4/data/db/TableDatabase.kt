package com.example.rsandroidtask4.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized
import com.example.rsandroidtask4.data.db.entity.TableItemEntity

@Database(entities = [TableItemEntity::class], version = 1, exportSchema = false)

abstract class TableDatabase : RoomDatabase() {

    abstract fun tableDao(): TableDao

    companion object {
        @Volatile
        private var INSTANCE: TableDatabase? = null

        @InternalCoroutinesApi
        fun getDatabase(context: Context): TableDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) return tempInstance

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TableDatabase::class.java,
                    "table_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}