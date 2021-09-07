package com.example.rsandroidtask4.ui.fragments.list

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rsandroidtask4.R
import com.example.rsandroidtask4.databinding.ItemListBinding
import com.example.rsandroidtask4.presentation.list.ListViewModel
import com.example.rsandroidtask4.presentation.list.ListViewModelFactory
import com.example.rsandroidtask4.ui.fragments.list.adapter.EmployeeAdapter
import com.example.rsandroidtask4.ui.fragments.list.swipegesture.SwipeHelper
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class ListFragment : Fragment() {

    private val viewModel: ListViewModel by viewModels {
        ListViewModelFactory()
    }

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

        setHasOptionsMenu(true)

        /*    initSortButton()*/
        Log.d(TAG, "onViewCreated")
        binding.apply {
            itemListRecycler.adapter = EmployeeAdapter()
            itemListRecycler.layoutManager = LinearLayoutManager(context)
            SwipeHelper(viewModel::deleteFromDb, requireContext()).attachToRecyclerView(
                itemListRecycler
            )
        }

        viewModel.readAllEmployees.observe(viewLifecycleOwner, { employee ->
            adapter?.submitList(employee)
        })

        onFloatingButtonClickListener()
        onSettingsButtonListener()

        super.onViewCreated(view, savedInstanceState)
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

   /* override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController()
        return when(item.itemId){
            R.id.filter_icon ->{
                val menuItemView: View = activity?.findViewById(item.itemId) as View
                showMenu(R.menu.filter, menuItemView)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }*/


  /*  private fun showMenu(@MenuRes menuRes: Int, anchor: View) {
        PopupMenu(requireContext(), anchor).apply {
            inflate(menuRes)

            setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.sort_by_name -> Log.d(TAG,"clicked name")

                        *//*viewModel.nameSortedEmployees.onEach(::renderItems)
                        .launchIn(lifecycleScope)*//*
                    R.id.sort_by_surname -> viewModel.surnameSortedEmployees.onEach(::renderItems)
                }
                true
            }
        }
    }*/

    /*  private fun initSortButton() {
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

    private fun <T> views(block: ItemListBinding.() -> T): T? = binding.block()

    companion object {
        const val TAG = "myLog"
    }
}

