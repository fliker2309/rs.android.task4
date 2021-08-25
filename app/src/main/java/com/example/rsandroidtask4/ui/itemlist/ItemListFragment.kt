package com.example.rsandroidtask4.ui.itemlist

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rsandroidtask4.R
import com.example.rsandroidtask4.data.db.database.ItemDatabase
import com.example.rsandroidtask4.data.db.entity.Item
import com.example.rsandroidtask4.data.db.repository.ItemRepository
import com.example.rsandroidtask4.databinding.ItemListBinding
import com.example.rsandroidtask4.databinding.ViewHolderItemBinding
import com.example.rsandroidtask4.presentation.itemList.ItemListViewModel
import com.example.rsandroidtask4.presentation.itemList.ItemListViewModelFactory
import com.example.rsandroidtask4.ui.itemlist.adapter.ItemAdapter
import com.example.rsandroidtask4.ui.itemlist.adapter.SwipeGesture
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

        views {
            itemListRecycler.adapter = ItemAdapter()
            itemListRecycler.layoutManager = LinearLayoutManager(context)
            addNewItemFloatingButton.setOnClickListener {
                addNewItem?.openAddItemFragment()
            }
        }

        viewModel.items.onEach(::renderItems).launchIn(lifecycleScope)

        onFloatingButtonClickListener()
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.sort_by_name) {
            Toast.makeText(
                requireContext(),
                "You clicked Sort by name",
                Toast.LENGTH_LONG
            ).show()
            sortItemsByName()
        }

        return super.onOptionsItemSelected(item)
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

    private fun sortItemsByAge() {
        viewModel.sortItemsByAge()
    }

    private fun sortItemsByName() {
        viewModel.sortItemsByName()
    }

    private fun sortItemsByBreed() {
        viewModel.sortItemsByBreed()
    }

    companion object {
        fun newInstance() = ItemListFragment()
        const val TAG = "myLog"
    }

}