package com.example.rsandroidtask4.data.db.repository

import com.example.rsandroidtask4.data.db.dao.ItemDao
import com.example.rsandroidtask4.data.db.entity.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class ItemRepository(private val itemDao: ItemDao) {

    fun readItemsFromDb(): Flow<List<Item>> = itemDao.readItemsFromDb()

    suspend fun insertItemInDb(item: Item) = itemDao.insertItemInDb(item)

    suspend fun deleteItemFromDb(item: Item) = itemDao.deleteItemFromDb(item)
}