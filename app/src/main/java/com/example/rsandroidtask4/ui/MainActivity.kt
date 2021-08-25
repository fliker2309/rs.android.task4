package com.example.rsandroidtask4.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rsandroidtask4.databinding.ActivityMainBinding
import com.example.rsandroidtask4.ui.addnewitem.AddNewItemFragment
import com.example.rsandroidtask4.ui.itemlist.ItemListFragment
import com.example.rsandroidtask4.ui.navigationinterface.NavigationInterface
import kotlinx.coroutines.InternalCoroutinesApi

class MainActivity : AppCompatActivity(), NavigationInterface {

    private lateinit var binding: ActivityMainBinding

    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            openItemListFragment()
        }
    }

    @InternalCoroutinesApi
    private fun openItemListFragment() {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, ItemListFragment.newInstance())
            .commit()
    }

    override fun openAddItemFragment() {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, AddNewItemFragment.newInstance())
            .commit()
    }

    @InternalCoroutinesApi
    override fun backToItemList() {
        openItemListFragment()
    }

}