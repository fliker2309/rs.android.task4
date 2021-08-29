package com.example.rsandroidtask4.presentation.additem

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rsandroidtask4.data.db.entity.Employee
import com.example.rsandroidtask4.data.db.repository.EmployeeRepository
import com.example.rsandroidtask4.data.locator.ServiceLocator.locateLazy
import kotlinx.coroutines.launch

class AddItemViewModel : ViewModel() {

    private val repository: EmployeeRepository by locateLazy()

    fun addNewEmployee(employee: Employee) {
        viewModelScope.launch { repository.insertEmployee((createEmployee(employee))) }
    }

    private fun createEmployee(employee: Employee) = Employee(
        name = employee.name,
        surname = employee.surname,
        age = employee.age,
        position = employee.position,
        experience = employee.experience
    )
}
