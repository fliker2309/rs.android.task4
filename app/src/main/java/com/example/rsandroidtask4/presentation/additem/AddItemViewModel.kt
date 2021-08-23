package com.example.rsandroidtask4.presentation.additem

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rsandroidtask4.data.db.entity.Item
import com.example.rsandroidtask4.data.db.repository.ItemRepository
import com.example.rsandroidtask4.data.locator.ServiceLocator.locateLazy
import kotlinx.coroutines.launch

class AddItemViewModel : ViewModel() {

    private val repository: ItemRepository by locateLazy()

    fun addNewItem(item: Item) {
        viewModelScope.launch { repository.insertItemInDb(createItem(item)) }
    }

    private fun createItem(item: Item) = Item(
        id = item.id,
        name = item.name,
        age = item.age,
        breed = item.breed

    )
}
