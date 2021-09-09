package com.example.rsandroidtask4.presentation.ui

import androidx.lifecycle.*
import com.example.rsandroidtask4.data.db.entity.Employee
import com.example.rsandroidtask4.data.db.repository.EmployeeRepository
import com.example.rsandroidtask4.data.locator.ServiceLocator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val repository: EmployeeRepository by ServiceLocator.locateLazy()

    fun addNewEmployee(employee: Employee) {
        viewModelScope.launch { repository.insertEmployee((createEmployee(employee))) }
    }

    fun updateEmployee(employee: Employee){
        viewModelScope.launch { repository.updateEmployee(employee) }
    }

    fun deleteFromDb(employee: Employee) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteEmployee(employee)
        }
    }

    private var chosenSort: MutableLiveData<String> = MutableLiveData("name")
    var employeeListLiveData: LiveData<List<Employee>> = Transformations.switchMap(chosenSort) { order ->
        repository.getEmployees(order)
    }

    fun sortBy(order: String) {
        chosenSort.value = order
    }

    private fun createEmployee(employee: Employee) = Employee(
        name = employee.name,
        surname = employee.surname,
        age = employee.age,
        position = employee.position,
        experience = employee.experience
    )
}