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
    /*   val nameSortedEmployees = repository.sortEmployeesByName().asLiveDataFlow()*/
    /*   val ageSortedEmployees = repository.sortEmployeesByAge().asLiveDataFlow()
       val positionSortedEmployees = repository.sortEmployeesByPosition().asLiveDataFlow()
       val surnameSortedEmployees = repository.sortEmployeesBySurname().asLiveDataFlow()
       val experienceSortedEmployees = repository.sortEmployeesByExperience().asLiveDataFlow()*/

    fun sortByName() {
        viewModelScope.launch { repository.sortEmployeesByName().asLiveDataFlow() }
    }

    fun sortBySurname() {
        viewModelScope.launch { repository.sortEmployeesBySurname().asLiveDataFlow() }
    }

    fun sortByAge() {
        viewModelScope.launch { repository.sortEmployeesByAge().asLiveDataFlow() }
    }

    fun sortByPosition() {
        viewModelScope.launch { repository.sortEmployeesByExperience().asLiveDataFlow() }
    }

    fun sortByExperience() {
        viewModelScope.launch { repository.sortEmployeesByExperience().asLiveDataFlow() }
    }

    fun deleteFromDb(employee: Employee) {
        viewModelScope.launch { repository.deleteEmployee(employee) }
    }

    //для отдачи последнего состояния если активити было уничтожено
    private fun <T> Flow<T>.asLiveDataFlow() =
        shareIn(viewModelScope, SharingStarted.Eagerly, replay = 1)

}