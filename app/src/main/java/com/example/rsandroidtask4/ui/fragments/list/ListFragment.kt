package com.example.rsandroidtask4.ui.fragments.list

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope

import com.example.rsandroidtask4.R

import com.example.rsandroidtask4.data.db.entity.Employee

import com.example.rsandroidtask4.databinding.ItemListBinding

import com.example.rsandroidtask4.presentation.list.ListViewModel
import com.example.rsandroidtask4.presentation.list.ListViewModelFactory
import com.example.rsandroidtask4.ui.fragments.list.adapter.EmployeeAdapter
import com.example.rsandroidtask4.ui.NavigationInterface
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@InternalCoroutinesApi
class ListFragment : Fragment() {

    private val viewModel: ListViewModel by viewModels {
        ListViewModelFactory()
    }
    private var binding: ItemListBinding? = null
    private var addNewItem: NavigationInterface? = null
    private val adapter: EmployeeAdapter? get() = views { itemListRecycler.adapter as? EmployeeAdapter }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is NavigationInterface)
            addNewItem = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ItemListBinding.inflate(inflater).also { binding = it }.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeUi()
        binding?.toolbar?.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.sort_by_name -> viewModel.nameSortedItems.onEach(::renderItems)
                    .launchIn(lifecycleScope)
                R.id.sort_by_breed -> viewModel.breedSortedItems.onEach(::renderItems)
                    .launchIn(lifecycleScope)
                R.id.sort_by_age -> viewModel.ageSortedItems.onEach(::renderItems)
                    .launchIn(lifecycleScope)
            }
            true
        }

        views {
            itemListRecycler.adapter = EmployeeAdapter()
            itemListRecycler.layoutManager = LinearLayoutManager(context)
            addNewItemFloatingButton.setOnClickListener {
                addNewItem?.openAddItemFragment()
            }
            SwipeHelper(viewModel::deleteFromDb, requireContext()).attachToRecyclerView(
                itemListRecycler
            )

        }

        subscribeUi()
        onFloatingButtonClickListener()
        /*  onClearTableButtonListener()*/
    }


    override fun onDetach() {
        super.onDetach()
        addNewItem = null
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.filter, menu)
        Log.d(TAG, "menu was created")
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun subscribeUi() {
        viewModel.items.onEach(::renderItems).launchIn(lifecycleScope)
    }


    private fun onClearTableButtonListener() {
        binding?.clearTableButton?.setOnClickListener {
            viewModel.deleteAllItems()
            Log.d(TAG, "delete all was clicked")
        }
    }

    private fun onFloatingButtonClickListener() {
        binding?.addNewItemFloatingButton?.setOnClickListener {
            addNewItem?.openAddItemFragment()
        }
    }

    private fun renderItems(employees: List<Employee>) {
        adapter?.submitList(employees)
    }

    private fun <T> views(block: ItemListBinding.() -> T): T? = binding?.block()

    companion object {
        fun newInstance() = ListFragment()
        const val TAG = "myLog"
    }

}

