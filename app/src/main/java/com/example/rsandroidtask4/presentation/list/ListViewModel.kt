package com.example.rsandroidtask4.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rsandroidtask4.data.db.entity.Employee
import com.example.rsandroidtask4.data.db.repository.EmployeeRepository
import com.example.rsandroidtask4.data.locator.ServiceLocator.locateLazy
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn

@InternalCoroutinesApi
class ListViewModel : ViewModel() {

    private val repository: EmployeeRepository by locateLazy()
    val items = repository.getEmployees().asLiveDataFlow()
    val nameSortedItems = repository.sortEmployeesByName().asLiveDataFlow()
    val ageSortedItems = repository.sortEmployeesByAge().asLiveDataFlow()
    val breedSortedItems = repository.sortEmployeesByPosition().asLiveDataFlow()

    fun deleteFromDb(employee: Employee) {
        viewModelScope.launch { repository.deleteEmployee(employee) }
    }

    //для отдачи последнего состояния если активити было уничтожено
    private fun <T> Flow<T>.asLiveDataFlow() =
        shareIn(viewModelScope, SharingStarted.Eagerly, replay = 1)

}