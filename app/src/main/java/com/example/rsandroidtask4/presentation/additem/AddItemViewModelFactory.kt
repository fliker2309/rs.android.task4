package com.example.rsandroidtask4.presentation.additem

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rsandroidtask4.data.db.repository.ItemRepository
import kotlinx.coroutines.InternalCoroutinesApi

class AddItemViewModelFactory(private val repository: ItemRepository) :
    ViewModelProvider.Factory {
    @InternalCoroutinesApi
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when(modelClass) {
        AddItemViewModel::class.java -> AddItemViewModel(repository)
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}