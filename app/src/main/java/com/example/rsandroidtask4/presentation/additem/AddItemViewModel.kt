package com.example.rsandroidtask4.presentation.additem

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rsandroidtask4.data.db.entity.Employee
import com.example.rsandroidtask4.data.db.repository.ItemRepository
import com.example.rsandroidtask4.data.locator.ServiceLocator.locateLazy
import kotlinx.coroutines.launch

class AddItemViewModel : ViewModel() {

    private val repository: ItemRepository by locateLazy()

    fun addNewItem(employee: Employee) {
        viewModelScope.launch { repository.insertItemInDb(createItem(employee)) }
    }

    private fun createItem(employee: Employee) = Employee(
        id = employee.id,
        name = employee.name,
        age = employee.age,
        breed = employee.breed

    )
}
