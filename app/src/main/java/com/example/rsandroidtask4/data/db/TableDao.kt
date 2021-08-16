package com.example.rsandroidtask4.data.db

import androidx.room.Dao
import androidx.room.Query


@Dao
interface TableDao {

    @Query("SELECT * FROM items")
    suspend fun readItemsFromDb(): List<Item>

    @Query("SELECT * ")

}