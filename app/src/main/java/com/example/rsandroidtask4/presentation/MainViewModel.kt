package com.example.rsandroidtask4.presentation

import androidx.lifecycle.*
import com.example.rsandroidtask4.data.db.entity.Employee
import com.example.rsandroidtask4.data.db.repository.EmployeeRepository
import com.example.rsandroidtask4.data.locator.ServiceLocator
import com.example.rsandroidtask4.ui.settings.SortOrder
import com.example.rsandroidtask4.ui.settings.UserPreferences
import com.example.rsandroidtask4.ui.settings.UserPreferencesRepository
import com.example.rsandroidtask4.ui.settings.UsesDatabase
/*import com.example.rsandroidtask4.ui.settings.SettingsLiveData*/
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

data class MainUiModel(
    val employees: List<Employee>, /*  val employees: LiveData<List<Employee>>,*/
    val sortOrder: SortOrder,
    val database: UsesDatabase
)

class MainViewModel(
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

  /*  private val userPreferencesRepository: UserPreferencesRepository by ServiceLocator.locateLazy()*/
    private val repository: EmployeeRepository by ServiceLocator.locateLazy()
    private val userPreferencesFlow = userPreferencesRepository.userPreferencesFlow

    //my experimental
    private val mainUiModelFlow = combine(
        repository.employees,
        userPreferencesFlow
    ) { employees: List<Employee>, userPreferences: UserPreferences ->
        return@combine MainUiModel(
            employees = sortEmployees(
                employees,
                userPreferences.sortOrder,
                userPreferences.usesDatabase
            ),
            database = userPreferences.usesDatabase,
            sortOrder = userPreferences.sortOrder
        )

    }
    val mainUiModel = mainUiModelFlow.asLiveData()


    private fun sortEmployees(
        employees: List<Employee>,
        sortOrder: SortOrder,
        database: UsesDatabase // добавить датабазу
    ): List<Employee> {
        return when (sortOrder) {
            SortOrder.BY_NAME -> employees.sortedBy { it.name }
            SortOrder.BY_SURNAME -> employees.sortedBy { it.surname }
            SortOrder.BY_AGE -> employees.sortedBy { it.age }
            SortOrder.BY_POSITION -> employees.sortedBy { it.position }
            SortOrder.BY_EXPERIENCE -> employees.sortedBy { it.experience }
        }
    }


    fun addNewEmployee(employee: Employee) {
        viewModelScope.launch { repository.roomInsertEmployee((createEmployee(employee))) }
    }

    fun updateEmployee(employee: Employee) {
        viewModelScope.launch { repository.roomUpdateEmployee(employee) }
    }

    fun deleteFromDb(employee: Employee) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.roomDeleteEmployee(employee)
        }
    }


    //удалить
    /* private var chosenSort: MutableLiveData<String> = MutableLiveData("name")
     var employeeListLiveData: LiveData<List<Employee>> =
         Transformations.switchMap(chosenSort) { order ->
             repository.roomGetEmployees(order)
         }

     fun sortBy(order: String) {
         chosenSort.value = order
     }*/

    private fun createEmployee(employee: Employee) = Employee(
        name = employee.name,
        surname = employee.surname,
        age = employee.age,
        position = employee.position,
        experience = employee.experience
    )
}