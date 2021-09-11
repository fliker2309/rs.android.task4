package com.example.rsandroidtask4.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rsandroidtask4.ui.settings.UserPreferencesRepository

class MainViewModelFactory(  private val userPreferencesRepository: UserPreferencesRepository): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T  = when (modelClass){
        MainViewModel::class.java -> MainViewModel(userPreferencesRepository)
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}