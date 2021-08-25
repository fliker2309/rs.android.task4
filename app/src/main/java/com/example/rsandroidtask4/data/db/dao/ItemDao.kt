package com.example.rsandroidtask4.data.db.dao

import androidx.room.*
import com.example.rsandroidtask4.data.db.entity.Item
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    @Query("SELECT * FROM items")
    fun readItemsFromDb(): Flow<List<Item>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItemInDb(item: Item)

    @Delete
    suspend fun deleteItemFromDb(item: Item)

    @Query("DELETE FROM items")
    suspend fun deleteAllItems()


    @Query("SELECT * FROM items ORDER BY age")
    fun sortItemsByAge(): Flow<List<Item>>

    @Query("SELECT * FROM items ORDER BY name")
    fun sortItemsByName(): Flow<List<Item>>

    @Query("SELECT * FROM items ORDER BY breed")
    fun sortItemsByBreed(): Flow<List<Item>>
}