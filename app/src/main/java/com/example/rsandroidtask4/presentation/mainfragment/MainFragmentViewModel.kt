package com.example.rsandroidtask4.presentation.mainfragment

import androidx.lifecycle.ViewModel
import com.example.rsandroidtask4.data.db.repository.ItemRepository
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class MainFragmentViewModel (private val repository: ItemRepository) : ViewModel(){
}