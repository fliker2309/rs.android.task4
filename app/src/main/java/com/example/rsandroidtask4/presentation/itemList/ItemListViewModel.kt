package com.example.rsandroidtask4.presentation.itemList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rsandroidtask4.data.db.entity.Item
import com.example.rsandroidtask4.data.db.repository.ItemRepository
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

@InternalCoroutinesApi
class ItemListViewModel(private val repository: ItemRepository) : ViewModel() {

    private var _mutableMainFragmentLiveData: MutableLiveData<List<Item>> =
        MutableLiveData(emptyList())
    val mainFragmentLiveData: LiveData<List<Item>>
        get() = _mutableMainFragmentLiveData

    private var _loadingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val loadingLiveData: LiveData<Boolean>
        get() = _loadingLiveData

    init {
        viewModelScope.launch {
            _mutableMainFragmentLiveData.value = repository.readItemsFromDb()
        }
    }




}