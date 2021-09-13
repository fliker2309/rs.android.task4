package com.example.rsandroidtask4.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rsandroidtask4.data.db.repository.EmployeeRepository
import com.example.rsandroidtask4.ui.settings.DatabaseSettingsLiveData
import com.example.rsandroidtask4.ui.settings.SettingsLiveData

class MainViewModelFactory(
    private val repository: EmployeeRepository,
    private val preferences: SettingsLiveData,
    private val dbPreferences: DatabaseSettingsLiveData
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository, preferences, dbPreferences) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}