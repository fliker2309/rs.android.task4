package com.example.rsandroidtask4.ui.fragments.update

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.rsandroidtask4.databinding.FragmentUpdateItemBinding

class UpdateItem : Fragment() {


    private var binding: FragmentUpdateItemBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        FragmentUpdateItemBinding.inflate(inflater, container, false).also { binding = it }.root
}