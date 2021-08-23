package com.example.rsandroidtask4.presentation.itemList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rsandroidtask4.data.db.repository.ItemRepository
import kotlinx.coroutines.InternalCoroutinesApi

class ItemListViewModelFactory : ViewModelProvider.Factory {
    @InternalCoroutinesApi
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        ItemListViewModel::class.java -> ItemListViewModel()
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}