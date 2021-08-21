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
    suspend fun deleteItemFromDb(item:Item)
}