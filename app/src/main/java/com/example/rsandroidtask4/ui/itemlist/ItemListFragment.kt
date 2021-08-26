package com.example.rsandroidtask4.ui.itemlist

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rsandroidtask4.R

import com.example.rsandroidtask4.data.db.entity.Item

import com.example.rsandroidtask4.databinding.ItemListBinding

import com.example.rsandroidtask4.presentation.itemList.ItemListViewModel
import com.example.rsandroidtask4.presentation.itemList.ItemListViewModelFactory
import com.example.rsandroidtask4.ui.itemlist.adapter.ItemAdapter
import com.example.rsandroidtask4.ui.itemlist.swipegesture.SwipeHelper
import com.example.rsandroidtask4.ui.navigationinterface.NavigationInterface
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@InternalCoroutinesApi
class ItemListFragment : Fragment() {

    private val viewModel: ItemListViewModel by viewModels {
        ItemListViewModelFactory()
    }
    private var binding: ItemListBinding? = null
    private var addNewItem: NavigationInterface? = null
    private val adapter: ItemAdapter? get() = views { itemListRecycler.adapter as? ItemAdapter }

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
            itemListRecycler.adapter = ItemAdapter()
            itemListRecycler.layoutManager = LinearLayoutManager(context)
            addNewItemFloatingButton.setOnClickListener {
                addNewItem?.openAddItemFragment()
            }
            SwipeHelper(viewModel::deleteFromDb, requireContext()).attachToRecyclerView(itemListRecycler)
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

    private fun renderItems(items: List<Item>) {
        adapter?.submitList(items)
    }

    private fun <T> views(block: ItemListBinding.() -> T): T? = binding?.block()

    companion object {
        fun newInstance() = ItemListFragment()
        const val TAG = "myLog"
    }

}

