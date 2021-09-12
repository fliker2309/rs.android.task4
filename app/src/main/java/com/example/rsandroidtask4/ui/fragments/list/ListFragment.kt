package com.example.rsandroidtask4.ui.fragments.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rsandroidtask4.R
import com.example.rsandroidtask4.databinding.ItemListBinding
import com.example.rsandroidtask4.presentation.MainViewModel
import com.example.rsandroidtask4.presentation.MainViewModelFactory
import com.example.rsandroidtask4.ui.fragments.add.dataStore
import com.example.rsandroidtask4.ui.fragments.list.adapter.EmployeeAdapter
import com.example.rsandroidtask4.ui.fragments.list.swipegesture.SwipeHelper
import com.example.rsandroidtask4.ui.settings.SortOrder
import com.example.rsandroidtask4.ui.settings.UserPreferencesRepository
import kotlinx.coroutines.InternalCoroutinesApi

const val USER_PREFERENCES_NAME = "user_preferences"


@InternalCoroutinesApi
class ListFragment : Fragment() {


    private lateinit var viewModel: MainViewModel

    private var _binding: ItemListBinding? = null
    private val binding: ItemListBinding
        get() = _binding!!

    private val adapter: EmployeeAdapter? get() = views { itemListRecycler.adapter as? EmployeeAdapter }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ItemListBinding.inflate(inflater).also { _binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(
                UserPreferencesRepository(requireContext().dataStore)
            )
        ).get(MainViewModel::class.java)

        viewModel.mainUiModel.observe(viewLifecycleOwner) { mainUiModel ->

                updateSort(mainUiModel.sortOrder)
            adapter?.submitList(mainUiModel.employees)

            // добавить обработку БД
        }


        Log.d(TAG, "onViewCreated")
        binding.apply {
            itemListRecycler.adapter = EmployeeAdapter()
            itemListRecycler.layoutManager = LinearLayoutManager(context)
            SwipeHelper(viewModel::deleteFromDb, requireContext()).attachToRecyclerView(
                itemListRecycler
            )
        }

        /*viewModel.sortBy(order)
        viewModel.employeeListLiveData.observe(viewLifecycleOwner, { employees ->
            employees?.let {
                updateUI(it)
            }
        })*/




        onFloatingButtonClickListener()
        onSettingsButtonListener()

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    /*  private fun updateUI(employees: List<Employee>) {
          adapter?.submitList(employees)
      }*/

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

    private fun updateSort(sortOrder: SortOrder) {
        return when (sortOrder) {
            SortOrder.BY_NAME -> viewModel.sortByName()
            SortOrder.BY_SURNAME -> viewModel.sortBySurname()
            SortOrder.BY_AGE -> viewModel.sortByAge()
            SortOrder.BY_POSITION -> viewModel.sortByPosition()
            SortOrder.BY_EXPERIENCE -> viewModel.sortByExperience()
        }
    }

    private fun <T> views(block: ItemListBinding.() -> T): T? = binding.block()

    companion object {
        const val TAG = "myLog"
    }
}
