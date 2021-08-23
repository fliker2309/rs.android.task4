package com.example.rsandroidtask4.presentation.additem

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rsandroidtask4.data.db.repository.ItemRepository
import kotlinx.coroutines.InternalCoroutinesApi

class AddItemViewModelFactory : ViewModelProvider.Factory {
    @InternalCoroutinesApi
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        AddItemViewModel::class.java -> AddItemViewModel()
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}
