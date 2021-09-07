package com.example.rsandroidtask4.presentation.list

import androidx.lifecycle.*
import com.example.rsandroidtask4.data.db.entity.Employee
import com.example.rsandroidtask4.data.db.repository.EmployeeRepository
import com.example.rsandroidtask4.data.locator.ServiceLocator.locateLazy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

@InternalCoroutinesApi
class ListViewModel : ViewModel() {

    private val repository: EmployeeRepository by locateLazy()

    fun deleteFromDb(employee: Employee) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteEmployee(employee)
        }
    }

    private var sort: MutableLiveData<String> = MutableLiveData("name")
    var employeeListLiveData: LiveData<List<Employee>> = Transformations.switchMap(sort) { order ->
        repository.getEmployees(order)
    }

    fun sortBy(order: String) {
        sort.value = order
    }
}