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
import kotlinx.coroutines.flow.shareIn

@InternalCoroutinesApi
class ItemListViewModel : ViewModel() {

    private val repository: ItemRepository by locateLazy()
    val items = repository.readItemsFromDb().asLiveDataFlow()

    fun deleteFromDb(item: Item) {
        viewModelScope.launch { repository.deleteItemFromDb(item) }
    }

    //для отдачи последнего состояния если активити было уничтожено
    private fun <T> Flow<T>.asLiveDataFlow() =
        shareIn(viewModelScope, SharingStarted.Eagerly, replay = 1)

}