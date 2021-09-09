package com.example.rsandroidtask4.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainViewModelFactory: ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T  = when (modelClass){
        MainViewModel::class.java -> MainViewModel()
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}