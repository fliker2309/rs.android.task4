package com.example.rsandroidtask4.data.db.repository

import com.example.rsandroidtask4.data.db.database.ItemDatabase
import com.example.rsandroidtask4.data.db.entity.Employee
import kotlinx.coroutines.flow.Flow


class ItemRepository(private val itemDatabase: ItemDatabase) {

    private val itemDao get() = itemDatabase.itemDao()

    fun readItemsFromDb(): Flow<List<Employee>> = itemDao.readItemsFromDb()

    fun sortItemsByAge(): Flow<List<Employee>> = itemDao.sortEmployeesByAge()
    fun sortItemsByName(): Flow<List<Employee>> = itemDao.sortEmployeesByName()
    fun sortItemsByBreed(): Flow<List<Employee>> = itemDao.sortEmployeesByPosition()

    suspend fun insertItemInDb(employee: Employee) = itemDao.insertEmployee(employee)

    suspend fun deleteItemFromDb(employee: Employee) = itemDao.deleteEmployee(employee)

    suspend fun deleteAllItems() = itemDao.wipeDatabase()
}