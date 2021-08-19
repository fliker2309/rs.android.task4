package com.example.rsandroidtask4.ui.itemlist

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rsandroidtask4.R
import com.example.rsandroidtask4.data.db.database.ItemDatabase
import com.example.rsandroidtask4.data.db.repository.ItemRepository
import com.example.rsandroidtask4.databinding.ItemListBinding
import com.example.rsandroidtask4.presentation.itemList.ItemListViewModel
import com.example.rsandroidtask4.presentation.itemList.ItemListViewModelFactory
import com.example.rsandroidtask4.ui.itemlist.adapter.ItemAdapter
import com.example.rsandroidtask4.ui.itemlist.adapter.SwipeGesture
import com.example.rsandroidtask4.ui.navigationinterface.NavigationInterface
import kotlinx.coroutines.InternalCoroutinesApi


class ItemListFragment : Fragment() {

    @InternalCoroutinesApi
    private val repository: ItemRepository by lazy {
        val db = ItemDatabase.getDatabase(this.requireContext().applicationContext)
        ItemRepository(db.itemDao())
    }

    @InternalCoroutinesApi
    private val itemListViewModel: ItemListViewModel by viewModels {
        ItemListViewModelFactory(repository)
    }

    private var _binding: ItemListBinding? = null
    private val binding: ItemListBinding
        get() = _binding!!

    private var addNewItem: NavigationInterface? = null
    private var itemAdapter: ItemAdapter? = null
    private lateinit var itemRecyclerView: RecyclerView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is NavigationInterface)
            addNewItem = context
    }

    @InternalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ItemListBinding.inflate(inflater, container, false)

        setUpRecycler()

        //TODO исправить метод updateItems на setItems!!!
        itemListViewModel.itemListLiveData.observe(viewLifecycleOwner) {
            (binding.recycler.adapter as ItemAdapter).updateItems(it)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onFloatingButtonClickListener()
    }


    override fun onDetach() {
        super.onDetach()
        addNewItem = null
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
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
        }
        /*  when (item.itemId) {
              R.id.sort_by_name -> Log.d(TAG,"clicked sort by name") *//*Toast.makeText(
                context,
                "You clicked Sort by name",
                Toast.LENGTH_LONG
            ).show()*//*
            R.id.sort_by_age -> Toast.makeText(
                context,
                "You clicked Sort by age",
                Toast.LENGTH_LONG
            ).show()
            R.id.sort_by_breed -> Toast.makeText(
                context,
                "You clicked Sort by breed",
                Toast.LENGTH_LONG
            ).show()
        }*/
        return super.onOptionsItemSelected(item)
    }

    private fun setUpRecycler() {
        itemAdapter = ItemAdapter()
        val swipeGesture = object : SwipeGesture(requireContext()) {


            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                when (direction) {
                    ItemTouchHelper.LEFT -> {
                        itemAdapter!!.deleteItem(viewHolder.adapterPosition)
                    }
                    ItemTouchHelper.RIGHT -> {
                        itemAdapter!!.deleteItem(viewHolder.adapterPosition)
                    }
                }
            }
        }
        val touchHelper = ItemTouchHelper(swipeGesture)
        touchHelper.attachToRecyclerView(itemRecyclerView)
        binding.recycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = itemAdapter
        }
        itemRecyclerView = binding.recycler

    }


    private fun onFloatingButtonClickListener() {
        binding.addNewItemFloatingButton.setOnClickListener {
            addNewItem?.openAddItemFragment()
        }
    }

    companion object {
        fun newInstance() = ItemListFragment()
        const val TAG = "myLog"
    }

}