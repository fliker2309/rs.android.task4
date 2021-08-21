package com.example.rsandroidtask4.ui

import android.app.Application
import android.content.Context
import com.example.rsandroidtask4.data.db.database.ItemDatabase
import com.example.rsandroidtask4.data.db.repository.ItemRepository
import com.example.rsandroidtask4.data.locator.ServiceLocator
import com.example.rsandroidtask4.data.locator.ServiceLocator.locate
import kotlinx.coroutines.InternalCoroutinesApi

class App : Application() {

    @InternalCoroutinesApi
    override fun onCreate() {
        super.onCreate()

        ServiceLocator.register<Context>(this)
        ServiceLocator.register<ItemDatabase>(ItemDatabase.getDatabase(locate()))
        ServiceLocator.register(ItemRepository(locate()))
    }
}