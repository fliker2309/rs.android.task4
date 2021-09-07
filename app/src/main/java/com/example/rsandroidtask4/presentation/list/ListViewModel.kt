package com.example.rsandroidtask4.presentation.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rsandroidtask4.data.db.entity.Employee
import com.example.rsandroidtask4.data.db.repository.EmployeeRepository
import com.example.rsandroidtask4.data.locator.ServiceLocator.locateLazy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

@InternalCoroutinesApi
class ListViewModel : ViewModel() {

    private val repository: EmployeeRepository by locateLazy()

    val readAllEmployees: LiveData<List<Employee>> = repository.getEmployees()

    fun deleteFromDb(employee: Employee) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteEmployee(employee)
        }
    }


    val nameSortedEmployees = repository.sortEmployeesByName()
    val ageSortedEmployees = repository.sortEmployeesByAge()
    val positionSortedEmployees = repository.sortEmployeesByPosition()
    val surnameSortedEmployees = repository.sortEmployeesBySurname()
    val experienceSortedEmployees = repository.sortEmployeesByExperience()

    fun updateSortStateById(newSortOrderId: Int): List<Employee> {
        readAllEmployees.value?.let { list ->
            return when (newSortOrderId) {
                0 -> {
                    Log.i(TAG, "sortedList: 0")
                    list.sortedBy { it.name }
                }
                1 -> {
                    Log.i(TAG, "sortedList: 1")
                    list.sortedBy { it.surname }
                }

                2 -> {
                    Log.i(TAG, "sortedList: 2")
                    list.sortedBy { it.age }
                }

                3 -> {
                    Log.i(TAG, "sortedList: 3")
                    list.sortedBy { it.position }
                }

                4 -> {
                    Log.i(TAG, "sortedList: 4")
                    list.sortedBy { it.experience }
                }
                else -> list
            }
        }
        return emptyList()
    }


    fun sortByName() {
        viewModelScope.launch { repository.sortEmployeesByName() }
    }

    fun sortBySurname() {
        viewModelScope.launch { repository.sortEmployeesBySurname() }
    }

    fun sortByAge() {
        viewModelScope.launch { repository.sortEmployeesByAge() }
    }

    fun sortByPosition() {
        viewModelScope.launch { repository.sortEmployeesByExperience() }
    }

    fun sortByExperience() {
        viewModelScope.launch { repository.sortEmployeesByExperience() }
    }

    companion object {
        const val TAG = "myLog"
    }
}