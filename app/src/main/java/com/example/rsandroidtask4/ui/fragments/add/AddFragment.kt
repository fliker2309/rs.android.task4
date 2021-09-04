package com.example.rsandroidtask4.ui.fragments.add

import android.os.Bundle
import android.text.TextUtils
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
                findNavController().navigate(R.id.action_addFragment_to_listFragment)
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
                findNavController().navigate(R.id.action_addFragment_to_listFragment)
            }
            /*  toolbar.setOnClickListener {
                  findNavController().navigate(R.id.action_addItemFragment_to_ListFragment)
              }*/
            addToTableButton.setOnClickListener {
                saveEmployee()
            }
        }
    }

    private fun saveEmployee() {
        views {
            val inputName = textInputName.text.toString()
            val inputSurname = textInputSurname.text.toString()
            val inputAge = textInputAge.text.toString()
            val inputPosition = textInputPosition.text.toString()
            val inputExperience = textInputExperience.text.toString()

            if (inputCheck(inputName, inputSurname, inputAge, inputPosition, inputExperience)) {
                val employee = Employee(
                    name = inputName,
                    surname = inputSurname,
                    age = inputAge,
                    position = inputPosition,
                    experience = inputExperience
                )
                viewModel.addNewEmployee(employee)
                findNavController().navigate(R.id.action_addFragment_to_listFragment)
                Toast.makeText(context, "Employee successful added", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(context, "Please, input all fields", Toast.LENGTH_SHORT).show()

            }
        }
    }

    private fun inputCheck(
        name: String,
        surname: String,
        age: String,
        position: String,
        experience: String
    ): Boolean {
        return !(TextUtils.isEmpty(name) || TextUtils.isEmpty(surname) || TextUtils.isEmpty(age) || TextUtils.isEmpty(
            position
        ) || TextUtils.isEmpty(experience))
    }
}