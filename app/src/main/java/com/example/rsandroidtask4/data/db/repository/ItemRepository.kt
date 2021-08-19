package com.example.rsandroidtask4.data.db.repository

import com.example.rsandroidtask4.data.db.dao.ItemDao
import com.example.rsandroidtask4.data.db.entity.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ItemRepository(private val itemDao: ItemDao) {
    suspend fun readItemsFromDb(): List<Item> = withContext(Dispatchers.IO) {
        itemDao.readItemsFromDb()
    }

    //для редактирования
    suspend fun getItemByIdFromDb(id: Int): Item = withContext(Dispatchers.IO) {
        itemDao.getItemByIdFromDb(id)
    }

    suspend fun insertItemInDb(item: Item) = withContext(Dispatchers.IO) {
        itemDao.insertItemInDb(item)
    }

    suspend fun deleteItemFromDb(item: Item) = withContext(Dispatchers.IO){
        itemDao.deleteItemFromDb(item)
    }
}