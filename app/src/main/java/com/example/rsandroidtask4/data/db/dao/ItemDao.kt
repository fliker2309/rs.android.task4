package com.example.rsandroidtask4.data.db.dao

import androidx.room.*
import com.example.rsandroidtask4.data.db.entity.Employee
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    @Query("SELECT * FROM items")
    fun readItemsFromDb(): Flow<List<Employee>>

    @Query("SELECT * FROM items WHERE id=(:id)")
    fun getItem(id: Int): Flow<Employee?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItemInDb(employee: Employee)

    @Delete
    suspend fun deleteItemFromDb(employee: Employee)

    @Query("DELETE FROM items")
    suspend fun deleteAllItems()

    @Query("SELECT * FROM items ORDER BY age ASC")
    fun sortItemsByAge(): Flow<List<Employee>>

    @Query("SELECT * FROM items ORDER BY name ASC")
    fun sortItemsByName(): Flow<List<Employee>>

    @Query("SELECT * FROM items ORDER BY breed ASC")
    fun sortItemsByBreed(): Flow<List<Employee>>
}