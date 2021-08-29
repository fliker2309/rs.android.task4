package com.example.rsandroidtask4.data.db.repository

import com.example.rsandroidtask4.data.db.database.ItemDatabase
import com.example.rsandroidtask4.data.db.entity.Employee
import kotlinx.coroutines.flow.Flow


class ItemRepository(private val itemDatabase: ItemDatabase) {

    private val itemDao get() = itemDatabase.itemDao()

    fun readItemsFromDb(): Flow<List<Employee>> = itemDao.readItemsFromDb()

    fun sortItemsByAge(): Flow<List<Employee>> = itemDao.sortItemsByAge()
    fun sortItemsByName(): Flow<List<Employee>> = itemDao.sortItemsByName()
    fun sortItemsByBreed(): Flow<List<Employee>> = itemDao.sortItemsByBreed()

    suspend fun insertItemInDb(employee: Employee) = itemDao.insertItemInDb(employee)

    suspend fun deleteItemFromDb(employee: Employee) = itemDao.deleteItemFromDb(employee)

    suspend fun deleteAllItems() = itemDao.deleteAllItems()
}