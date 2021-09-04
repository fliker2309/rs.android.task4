package com.example.rsandroidtask4.ui.fragments.update

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.rsandroidtask4.R
import com.example.rsandroidtask4.databinding.FragmentUpdateItemBinding

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private var _binding: FragmentUpdateItemBinding? = null
    private val binding: FragmentUpdateItemBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpdateItemBinding.inflate(inflater, container, false)
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
        initListeners()
        super.onViewCreated(view, savedInstanceState)

    }


    private fun <T> views(block: FragmentUpdateItemBinding.() -> T): T? = binding.block()

    private fun initListeners() {
        views {
            backButton.setOnClickListener {
                findNavController().navigate(R.id.action_updateFragment_to_listFragment)
            }

        }
    }
}