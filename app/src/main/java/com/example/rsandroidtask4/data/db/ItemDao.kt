package com.example.rsandroidtask4.data.db

import androidx.room.*
import com.example.rsandroidtask4.data.db.entity.Item

@Dao
interface ItemDao {

    @Query("SELECT * FROM items")
    suspend fun readItemsFromDb(): List<Item>

    @Query("SELECT * FROM items WHERE id = :id LIMIT 1")
    suspend fun getItemByIdFromDb(id:Int) : Item

    @Update
    suspend fun updateItemInDb(item: Item)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItemsInDb(item: List<Item>)
}