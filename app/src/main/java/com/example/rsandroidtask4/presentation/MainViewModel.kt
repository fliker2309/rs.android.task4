package com.example.rsandroidtask4.presentation

/*import com.example.rsandroidtask4.ui.settings.SettingsLiveData*/
import androidx.lifecycle.*
import com.example.rsandroidtask4.data.db.entity.Employee
import com.example.rsandroidtask4.data.db.repository.EmployeeRepository

import com.example.rsandroidtask4.ui.settings.DatabaseSettingsLiveData
import com.example.rsandroidtask4.ui.settings.SettingsLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/*data class MainUiModel(
 *//*   val employees: Flow<List<Employee>>,*//* *//*  val employees: LiveData<List<Employee>>,*//*
    val sortOrder: SortOrder,
    val database: UsesDatabase
)*/

class MainViewModel(
    private val repository: EmployeeRepository,
    private val preferences: SettingsLiveData,
    private val dbPreferences: DatabaseSettingsLiveData
    /*  private val dataStoreRepository: DataStoreRepository*/
) : ViewModel() {

    fun getPreferences() = preferences
    fun getDbPreferences() = dbPreferences

    private val database get() = getDbPreferences().value ?: 0

    private val _allEmployees
        get() = repository.getEmployees(database).asLiveData() as MutableLiveData<List<Employee>>
    val allEmployees: LiveData<List<Employee>> get() = _allEmployees

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


    /*val preferences = dataStoreRepository.dataStoreFlow.collect {
        usesDatabase ->
    }*/


/*

    */
/*  private val repository: EmployeeRepository by ServiceLocator.locateLazy()*//*

    private val userPreferencesFlow = userPreferencesRepository.userPreferencesFlow

     fun getDatabasePreference() = viewModelScope.launch {
         userPreferencesRepository.saveDatabaseImplementation()
     }
    suspend fun getSortPreference() = userPreferencesRepository.saveSortMethod()

    private val database get() = getDatabasePreference().value?: "room"

    private val _allEmployees get() = repository
*/


    /*  val getPreferencesFromDataStore = userPreferencesFlow.asLiveData()

      private val database get() = getPreferencesFromDataStore.*/


    //my experimental, нужно к этому моменту знать какая БД выбрана
    /*   private val mainUiModelFlow = combine(
           repository.getEmployees(),
           userPreferencesFlow
       ) { *//*employees : Flow<List<Employee>>,*//* userPreferences ->
        return@combine MainUiModel(
            employees = sortEmployees(
              *//*  employees,*//*
                userPreferences.sortOrder,
                userPreferences.usesDatabase
            ),
            database = userPreferences.usesDatabase,
            sortOrder = userPreferences.sortOrder
        )
    }
    val mainUiModel = mainUiModelFlow.asLiveData()

    private fun sortEmployees(
    *//*    employees: Flow<List<Employee>>,*//*
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
*/
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


  /*  fun addNewEmployee(employee: Employee, database: UsesDatabase) {
        viewModelScope.launch { repository.insertEmployee((createEmployee(employee))) }
    }

    fun updateEmployee(employee: Employee, database: UsesDatabase) {
        viewModelScope.launch { repository.roomUpdateEmployee(employee) }
    }

    fun deleteFromDb(employee: Employee, database: UsesDatabase) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.roomDeleteEmployee(employee)
        }
    }*/

    private fun createEmployee(employee: Employee) = Employee(
        name = employee.name,
        surname = employee.surname,
        age = employee.age,
        position = employee.position,
        experience = employee.experience
    )
}