package com.example.rsandroidtask4.presentation.update

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rsandroidtask4.data.db.entity.Employee
import com.example.rsandroidtask4.data.db.repository.EmployeeRepository
import com.example.rsandroidtask4.data.locator.ServiceLocator.locateLazy
import kotlinx.coroutines.launch

class UpdateViewModel : ViewModel() {

    private val repository: EmployeeRepository by locateLazy()

    fun updateEmployee(employee: Employee){
        viewModelScope.launch { repository.updateEmployee(employee) }
    }
}