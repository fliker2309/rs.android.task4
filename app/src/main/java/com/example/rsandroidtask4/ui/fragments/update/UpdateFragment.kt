package com.example.rsandroidtask4.ui.fragments.update

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.rsandroidtask4.R
import com.example.rsandroidtask4.databinding.FragmentUpdateItemBinding

class UpdateFragment : Fragment() {


    private var binding: FragmentUpdateItemBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        FragmentUpdateItemBinding.inflate(inflater, container, false).also { binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initListeners()




        super.onViewCreated(view, savedInstanceState)

    }


    private fun <T> views(block: FragmentUpdateItemBinding.() -> T): T? = binding?.block()

    private fun initListeners() {
        views {
            backButton.setOnClickListener {
                findNavController().navigate(R.id.action_updateFragment_to_listFragment)
            }

        }
    }
}