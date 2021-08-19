package com.example.rsandroidtask4.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rsandroidtask4.R
import com.example.rsandroidtask4.databinding.ActivityMainBinding
import com.example.rsandroidtask4.ui.addnewitem.AddNewItemFragment
import com.example.rsandroidtask4.ui.itemlist.ItemListFragment
import com.example.rsandroidtask4.ui.navigationinterface.NavigationInterface

private const val TAG = "myLog"

class MainActivity : AppCompatActivity(), NavigationInterface {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            openItemListFragment()
        }
    }

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

    override fun backToItemList() {
        openItemListFragment()
    }


}