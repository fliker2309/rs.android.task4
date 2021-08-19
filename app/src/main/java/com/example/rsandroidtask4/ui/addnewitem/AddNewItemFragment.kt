package com.example.rsandroidtask4.ui.addnewitem

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.example.rsandroidtask4.databinding.FragmentAddNewItemBinding
import com.example.rsandroidtask4.ui.navigationinterface.NavigationInterface

class AddNewItemFragment : Fragment() {

    private var _binding: FragmentAddNewItemBinding? = null
    private val binding: FragmentAddNewItemBinding
        get() = _binding!!

    private var backToList: NavigationInterface? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is NavigationInterface)
            backToList = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddNewItemBinding.inflate(inflater, container, false)

        initListeners()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
        _binding = null
    }


    private fun initListeners() {
        binding.apply {
            backButton.setOnClickListener {
                onBackClickListener()
            }
            toolbar.setOnClickListener {
                onBackClickListener()
            }
        }
    }

    private fun onBackClickListener() {
        backToList?.backToItemList()
    }


    companion object {
        fun newInstance() = AddNewItemFragment()
    }
}