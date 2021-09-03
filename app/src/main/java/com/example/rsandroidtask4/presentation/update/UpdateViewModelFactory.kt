package com.example.rsandroidtask4.presentation.update

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.InternalCoroutinesApi

class UpdateViewModelFactory : ViewModelProvider.Factory {
    @InternalCoroutinesApi
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when(modelClass) {
     UpdateViewModel::class.java -> UpdateViewModel()
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}