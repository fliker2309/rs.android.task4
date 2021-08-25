package com.example.rsandroidtask4.presentation.itemList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rsandroidtask4.data.db.entity.Item
import com.example.rsandroidtask4.data.db.repository.ItemRepository
import com.example.rsandroidtask4.data.locator.ServiceLocator.locateLazy
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.runBlocking

@InternalCoroutinesApi
class ItemListViewModel : ViewModel() {

    private val repository: ItemRepository by locateLazy()
    val items = repository.readItemsFromDb().asLiveDataFlow()
    val nameSortedItems = repository.sortItemsByName().asLiveDataFlow()
    val ageSortedItems = repository.sortItemsByAge().asLiveDataFlow()
    val breedSortedItems = repository.sortItemsByBreed().asLiveDataFlow()

    fun deleteFromDb(item: Item) {
        viewModelScope.launch { repository.deleteItemFromDb(item) }
    }

    fun deleteAllItems() {
        viewModelScope.launch { repository.deleteAllItems() }
    }

    //для отдачи последнего состояния если активити было уничтожено
    private fun <T> Flow<T>.asLiveDataFlow() =
        shareIn(viewModelScope, SharingStarted.Eagerly, replay = 1)

}