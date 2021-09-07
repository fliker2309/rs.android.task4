package com.example.rsandroidtask4.presentation.list

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
            repository.deleteEmployee(employee) }
    }


    /*

      val nameSortedEmployees = repository.sortEmployeesByName().asLiveDataFlow()
      val ageSortedEmployees = repository.sortEmployeesByAge().asLiveDataFlow()
      val positionSortedEmployees = repository.sortEmployeesByPosition().asLiveDataFlow()
      val surnameSortedEmployees = repository.sortEmployeesBySurname().asLiveDataFlow()
      val experienceSortedEmployees = repository.sortEmployeesByExperience().asLiveDataFlow()*/

  /*  fun updateSortStateById(newSortOrderId: Int) {

    }*/


    /*  fun sortByName() {
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
      }*/


}