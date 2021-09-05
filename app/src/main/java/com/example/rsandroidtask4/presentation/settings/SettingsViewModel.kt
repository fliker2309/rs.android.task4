package com.example.rsandroidtask4.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rsandroidtask4.data.db.repository.EmployeeRepository
import com.example.rsandroidtask4.data.locator.ServiceLocator.locateLazy
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn

class SettingsViewModel : ViewModel() {

  /*  private val repository:EmployeeRepository by locateLazy()*/

    private fun <T> Flow<T>.asLiveDataFlow() =
        shareIn(viewModelScope, SharingStarted.Eagerly, replay = 1)
}