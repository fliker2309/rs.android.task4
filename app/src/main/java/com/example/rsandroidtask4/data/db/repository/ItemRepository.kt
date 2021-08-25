package com.example.rsandroidtask4.data.db.repository

import com.example.rsandroidtask4.data.db.database.ItemDatabase
import com.example.rsandroidtask4.data.db.entity.Item
import kotlinx.coroutines.flow.Flow


class ItemRepository(private val itemDatabase: ItemDatabase) {

    private val itemDao get() = itemDatabase.itemDao()

    fun readItemsFromDb(): Flow<List<Item>> = itemDao.readItemsFromDb()

    fun sortItemsByAge(): Flow<List<Item>> = itemDao.sortItemsByAge()
    fun sortItemsByName(): Flow<List<Item>> = itemDao.sortItemsByName()
    fun sortItemsByBreed(): Flow<List<Item>> = itemDao.sortItemsByBreed()

    suspend fun insertItemInDb(item: Item) = itemDao.insertItemInDb(item)

    suspend fun deleteItemFromDb(item: Item) = itemDao.deleteItemFromDb(item)
}