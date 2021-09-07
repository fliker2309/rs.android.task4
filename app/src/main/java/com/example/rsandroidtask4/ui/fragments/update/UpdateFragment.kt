package com.example.rsandroidtask4.ui.fragments.update

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.rsandroidtask4.R
import com.example.rsandroidtask4.data.db.entity.Employee
import com.example.rsandroidtask4.databinding.FragmentUpdateItemBinding
import com.example.rsandroidtask4.presentation.update.UpdateViewModel
import com.example.rsandroidtask4.presentation.update.UpdateViewModelFactory

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private var _binding: FragmentUpdateItemBinding? = null
    private val binding: FragmentUpdateItemBinding
        get() = _binding!!

    private val viewModel: UpdateViewModel by viewModels {
        UpdateViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpdateItemBinding.inflate(inflater, container, false)
        //saveArgs NavComponent
        binding.apply {
            textUpdateName.setText(args.currentEmployee.name)
            textUpdateSurname.setText(args.currentEmployee.surname)
            textUpdateAge.setText(args.currentEmployee.age)
            textUpdatePosition.setText(args.currentEmployee.position)
            textUpdateExperience.setText(args.currentEmployee.experience)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            updateButton.setOnClickListener {
                updateEmployee()
            }
            backButton.setOnClickListener {
                findNavController().popBackStack()
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun updateEmployee() {
        binding.run {
            val updateName = textUpdateName.text.toString()
            val updateSurname = textUpdateSurname.text.toString()
            val updateAge = textUpdateAge.text.toString()
            val updatePosition = textUpdatePosition.text.toString()
            val updateExperience = textUpdateExperience.text.toString()

            if (inputCheck(updateName, updateSurname, updateAge, updatePosition, updateExperience)
            ) {
                val updatedEmployee = Employee(
                    id = args.currentEmployee.id, //don't forget about ID!!!
                    name = updateName,
                    surname = updateSurname,
                    age = updateAge,
                    position = updatePosition,
                    experience = updateExperience
                )
                viewModel.updateEmployee(updatedEmployee)
                findNavController().navigate(R.id.action_updateFragment_to_listFragment)
                Toast.makeText(context, "Successfully updated", Toast.LENGTH_SHORT).show()

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
