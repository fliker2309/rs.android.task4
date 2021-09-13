package com.example.rsandroidtask4.presentation

import androidx.lifecycle.*
import com.example.rsandroidtask4.data.db.entity.Employee
import com.example.rsandroidtask4.data.db.repository.EmployeeRepository
import com.example.rsandroidtask4.ui.settings.DatabaseSettingsLiveData
import com.example.rsandroidtask4.ui.settings.SettingsLiveData
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: EmployeeRepository,
    private val preferences: SettingsLiveData,
    private val dbPreferences: DatabaseSettingsLiveData

) : ViewModel() {

    fun getPreferences() = preferences
    fun getDbPreferences() = dbPreferences

    private val database get() = getDbPreferences().value ?: 0

    private val _allEmployees
        get() = repository.getEmployees(database).asLiveData() as MutableLiveData<List<Employee>>
    private val allEmployees: LiveData<List<Employee>> get() = _allEmployees

    fun updateList() = allEmployees.map { allEmployees ->
        when (getPreferences().value){
            0 -> allEmployees.sortedBy { it.name }
            1 -> allEmployees.sortedBy { it.surname }
            2 -> allEmployees.sortedBy { it.age }
            3 -> allEmployees.sortedBy { it.position }
            4 -> allEmployees.sortedBy { it.experience }
            else -> allEmployees
        }
    }

     fun addNewEmployee(employee: Employee) = viewModelScope.launch { repository.insertEmployee(createEmployee(employee), database) }

     fun updateEmployee(employee: Employee) = viewModelScope.launch { repository.updateEmployee(employee, database) }

     fun deleteEmployee(employee:Employee) = viewModelScope.launch { repository.deleteEmployee(employee, database) }

    private fun createEmployee(employee: Employee) = Employee(
        name = employee.name,
        surname = employee.surname,
        age = employee.age,
        position = employee.position,
        experience = employee.experience
    )
}