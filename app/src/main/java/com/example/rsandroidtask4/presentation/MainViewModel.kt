package com.example.rsandroidtask4.presentation

import androidx.lifecycle.*
import com.example.rsandroidtask4.data.db.entity.Employee
import com.example.rsandroidtask4.data.db.repository.EmployeeRepository
import com.example.rsandroidtask4.data.locator.ServiceLocator
import com.example.rsandroidtask4.ui.settings.SortOrder
import com.example.rsandroidtask4.ui.settings.UserPreferencesRepository
import com.example.rsandroidtask4.ui.settings.UsesDatabase
/*import com.example.rsandroidtask4.ui.settings.SettingsLiveData*/
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

data class MainUiModel(
 /*   val employees: Flow<List<Employee>>,*/ /*  val employees: LiveData<List<Employee>>,*/
    val sortOrder: SortOrder,
    val database: UsesDatabase
)

class MainViewModel(
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {


    private val repository: EmployeeRepository by ServiceLocator.locateLazy()
    private val userPreferencesFlow = userPreferencesRepository.userPreferencesFlow

    //my experimental, нужно к этому моменту знать какая БД выбрана
    private val mainUiModelFlow = combine(
        repository.roomGetEmployees(),
        userPreferencesFlow
    ) { /*employees : Flow<List<Employee>>,*/ userPreferences ->
        return@combine MainUiModel(
            employees = sortEmployees(
              /*  employees,*/
                userPreferences.sortOrder,
                userPreferences.usesDatabase
            ),
            database = userPreferences.usesDatabase,
            sortOrder = userPreferences.sortOrder
        )
    }
    val mainUiModel = mainUiModelFlow.asLiveData()

    private fun sortEmployees(
    /*    employees: Flow<List<Employee>>,*/
        sortOrder: SortOrder,
        database: UsesDatabase // добавить датабазу
    ):Flow<List<Employee>> {
        val cursorUses = UsesDatabase.CURSOR.name
        val roomUses = UsesDatabase.ROOM.name
        return when (sortOrder) {
            SortOrder.BY_NAME -> sortByName
            SortOrder.BY_SURNAME -> sortBySurname
            SortOrder.BY_AGE -> sortByAge
            SortOrder.BY_POSITION -> sortByPosition
            SortOrder.BY_EXPERIENCE -> sortByExperience
        }

    }

   /*   private fun sortEmployees(
          employees: List<Employee>,
          sortOrder: SortOrder,
          database: UsesDatabase // добавить датабазу
      ): List<Employee> {
          val cursorUses = UsesDatabase.CURSOR.name
          val roomUses = UsesDatabase.ROOM.name
          if (database.name == roomUses) {
              return when (sortOrder) {
                  SortOrder.BY_NAME -> employees.sortedBy { it.name }
                  SortOrder.BY_SURNAME -> employees.sortedBy { it.surname }
                  SortOrder.BY_AGE -> employees.sortedBy { it.age }
                  SortOrder.BY_POSITION -> employees.sortedBy { it.position }
                  SortOrder.BY_EXPERIENCE -> sortByExperience *//*sortByExperience()*//*
            }
        } else { //изменить
            return when (sortOrder) {
                SortOrder.BY_NAME -> employees.sortedByDescending { it.name }
                SortOrder.BY_SURNAME -> employees.sortedByDescending { it.surname }
                SortOrder.BY_AGE -> employees.sortedByDescending { it.age }
                SortOrder.BY_POSITION -> employees.sortedByDescending { it.position }
                SortOrder.BY_EXPERIENCE -> employees.sortedByDescending { it.experience }
            }
        }
    }*/

 /*   fun sortMethod() {
        viewModelScope.launch {
            userPreferencesRepository.sortMethod()
        }
    }

    fun currentDBMS() {
        viewModelScope.launch {
            userPreferencesRepository.currentDBMS()
        }
    }*/


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


     private val sortByName = repository.roomSortByName()/*.asLiveData()*/
    /*fun sortByName() {
       viewModelScope.launch { repository.roomSortByName() }
    }*/
private val sortBySurname = repository.roomSortBySurname()/*.asLiveData()*/
   /* fun sortBySurname()  {
        viewModelScope.launch { repository.roomSortBySurname() }
    }*/

    private val sortByAge = repository.roomSortByAge()/*.asLiveData()*/
    /*fun sortByAge() {
        viewModelScope.launch { repository.roomSortByAge() }
    }*/

    private val sortByPosition = repository.roomSortByPosition()/*.asLiveData()*/
    /*fun sortByPosition() {
        viewModelScope.launch { repository.roomSortByPosition() }
    }*/

   private val sortByExperience = repository.roomSortByExperience()
   /* fun sortByExperience() {
        viewModelScope.launch { repository.roomSortByExperience() }
    }*/

    private fun createEmployee(employee: Employee) = Employee(
        name = employee.name,
        surname = employee.surname,
        age = employee.age,
        position = employee.position,
        experience = employee.experience
    )
}