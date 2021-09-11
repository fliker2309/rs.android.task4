package com.example.rsandroidtask4.presentation

import androidx.lifecycle.*
import com.example.rsandroidtask4.data.db.entity.Employee
import com.example.rsandroidtask4.data.db.repository.EmployeeRepository
import com.example.rsandroidtask4.data.locator.ServiceLocator
import com.example.rsandroidtask4.ui.settings.UserPreferencesRepository
/*import com.example.rsandroidtask4.ui.settings.SettingsLiveData*/
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val userPreferencesRepository: UserPreferencesRepository
): ViewModel() {

    private val repository: EmployeeRepository by ServiceLocator.locateLazy()
    private val userPreferencesFlow = userPreferencesRepository.userPreferencesFlow

    fun addNewEmployee(employee: Employee) {
        viewModelScope.launch { repository.roomInsertEmployee((createEmployee(employee))) }
    }

    fun updateEmployee(employee: Employee){
        viewModelScope.launch { repository.roomUpdateEmployee(employee) }
    }

    fun deleteFromDb(employee: Employee) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.roomDeleteEmployee(employee)
        }
    }


//удалить
    private var chosenSort: MutableLiveData<String> = MutableLiveData("name")
    var employeeListLiveData: LiveData<List<Employee>> = Transformations.switchMap(chosenSort) { order ->
        repository.roomGetEmployees(order)
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