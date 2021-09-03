package com.example.rsandroidtask4.ui.fragments.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.rsandroidtask4.R
import com.example.rsandroidtask4.data.db.entity.Employee
import com.example.rsandroidtask4.databinding.FragmentAddItemBinding
import com.example.rsandroidtask4.presentation.add.AddViewModel
import com.example.rsandroidtask4.presentation.add.AddViewModelFactory

class AddFragment : Fragment() {

    private var binding: FragmentAddItemBinding? = null

    private val viewModel: AddViewModel by viewModels {
        AddViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        FragmentAddItemBinding.inflate(inflater, container, false).also { binding = it }.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.action_addItemFragment_to_ListFragment)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun <T> views(block: FragmentAddItemBinding.() -> T): T? = binding?.block()

    private fun initListeners() {
        views {
            backButton.setOnClickListener {
                findNavController().navigate(R.id.action_addItemFragment_to_ListFragment)
            }
            toolbar.setOnClickListener {
                findNavController().navigate(R.id.action_addItemFragment_to_ListFragment)
            }
            addToTableButton.setOnClickListener {
                saveEmployee()
            }
        }
    }

    private fun saveEmployee() {
        views {
            val inputName = textInputName.text.toString().takeIf { it.isNotBlank() } ?: return@views
            val inputSurname =
                textInputSurname.text.toString().takeIf { it.isNotBlank() } ?: return@views
            val inputAge = textInputAge.text.toString().takeIf { it.isNotBlank() } ?: return@views
            val inputPosition =
                textInputPosition.text.toString().takeIf { it.isNotBlank() } ?: return@views
            val inputExperience =
                textInputExperience.text.toString().takeIf { it.isNotBlank() } ?: return@views

            val savedItem = Employee(
                name = inputName,
                surname = inputSurname,
                age = inputAge,
                position = inputPosition,
                experience = inputExperience
            )

            viewModel.addNewEmployee(savedItem)
            findNavController().navigate(R.id.action_addItemFragment_to_ListFragment)
            Toast.makeText(context, "Item successful added", Toast.LENGTH_LONG).show()
        }
    }
}