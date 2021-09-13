package com.example.rsandroidtask4.ui.fragments.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rsandroidtask4.R
import com.example.rsandroidtask4.databinding.ItemListBinding
import com.example.rsandroidtask4.presentation.MainViewModel
import com.example.rsandroidtask4.presentation.MainViewModelFactory
import com.example.rsandroidtask4.ui.App
import com.example.rsandroidtask4.ui.fragments.list.adapter.EmployeeAdapter
import com.example.rsandroidtask4.ui.settings.DatabaseSettingsLiveData
import com.example.rsandroidtask4.ui.settings.SortSettingsLiveData
import com.example.rsandroidtask4.utils.TAG
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class ListFragment : Fragment() {

    private val preferences by lazy {
        SortSettingsLiveData(
            PreferenceManager.getDefaultSharedPreferences(
                context?.applicationContext
            )
        )
    }
    private val dbPreferences by lazy {
        DatabaseSettingsLiveData(
            PreferenceManager.getDefaultSharedPreferences(
                context?.applicationContext
            )
        )
    }
    private val viewModel : MainViewModel by activityViewModels {
        MainViewModelFactory(
            (activity?.application as App)
                .repository,
            preferences,
            dbPreferences
        )
    }

    private var _binding: ItemListBinding? = null
    private val binding: ItemListBinding
        get() = _binding!!

    private val adapter: EmployeeAdapter
        get() = binding.itemListRecycler.adapter as EmployeeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ItemListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        Log.d(TAG, "onViewCreated")
        viewModel.getDbPreferences().observe(viewLifecycleOwner) {
            Log.d(TAG, "Database observer $it")
            viewModel.updateList().observe(viewLifecycleOwner) { employees ->
                Log.d(TAG, "Update list observer and list $employees")
                adapter.submitList(employees)
            }
        }

        viewModel.getPreferences().observe(viewLifecycleOwner) {
            Log.d(TAG, "Sort observer $it")
            viewModel.updateList().observe(viewLifecycleOwner) { employees ->
                Log.d(TAG, "Update list inside observer and list $employees")
                adapter.submitList(employees)
            }
        }

        binding.apply {
            itemListRecycler.adapter = EmployeeAdapter()
            itemListRecycler.layoutManager = LinearLayoutManager(context)
        }

        onFloatingButtonClickListener()
        onSettingsButtonListener()

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun onFloatingButtonClickListener() {
        binding.addNewItemFloatingButton.setOnClickListener {
            findNavController().navigate(R.id.action_ListFragment_to_addFragment)
        }
    }

    private fun onSettingsButtonListener() {
        binding.settingsButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_settingsFragment)
        }
    }
}
