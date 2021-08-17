package com.example.rsandroidtask4.data.db.dao

import androidx.room.*
import com.example.rsandroidtask4.data.db.entity.Item

@Dao
interface ItemDao {

    @Query("SELECT * FROM items")
    suspend fun readItemsFromDb(): List<Item>

    // для редактирования можно этим воспользоваться будет
    @Query("SELECT * FROM items WHERE id = :id LIMIT 1")
    suspend fun getItemByIdFromDb(id:Int) : Item

    @Update
    suspend fun updateItemInDb(item: Item)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItemInDb(item: Item)
}