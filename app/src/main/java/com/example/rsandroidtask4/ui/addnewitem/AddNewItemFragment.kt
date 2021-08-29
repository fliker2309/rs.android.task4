package com.example.rsandroidtask4.ui.addnewitem

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.rsandroidtask4.data.db.entity.Employee
import com.example.rsandroidtask4.databinding.FragmentAddNewItemBinding
import com.example.rsandroidtask4.presentation.additem.AddItemViewModel
import com.example.rsandroidtask4.presentation.additem.AddItemViewModelFactory
import com.example.rsandroidtask4.ui.navigationinterface.NavigationInterface

class AddNewItemFragment : Fragment() {

    private var binding: FragmentAddNewItemBinding? = null

    private var backToList: NavigationInterface? = null

    private val viewModel: AddItemViewModel by viewModels {
        AddItemViewModelFactory()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is NavigationInterface)
            backToList = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        FragmentAddNewItemBinding.inflate(inflater, container, false).also { binding = it }.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                onBackClickListener()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }


    override fun onDetach() {
        super.onDetach()
        backToList = null
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun <T> views(block: FragmentAddNewItemBinding.() -> T): T? = binding?.block()

    private fun initListeners() {
        views {
            backButton.setOnClickListener {
                onBackClickListener()
            }
            toolbar.setOnClickListener {
                onBackClickListener()
            }
            addToTableButton.setOnClickListener {
                saveItem()
            }
        }
    }

    private fun saveItem() {
        views {

            val inputAge = textInputAge.text.toString().takeIf { it.isNotBlank() } ?: return@views

            val inputName = textInputName.text.toString().takeIf { it.isNotBlank() } ?: return@views
            val inputBreed =
                textInputBreed.text.toString().takeIf { it.isNotBlank() } ?: return@views
            val savedItem = Employee(name = inputName, age = inputAge, position = inputBreed)

            viewModel.addNewItem(savedItem)
            backToList?.backToItemList()
            Toast.makeText(context, "Item successful added", Toast.LENGTH_LONG).show()
        }
    }

    private fun onBackClickListener() {
        backToList?.backToItemList()
    }

    companion object {
        fun newInstance() = AddNewItemFragment()
    }
}