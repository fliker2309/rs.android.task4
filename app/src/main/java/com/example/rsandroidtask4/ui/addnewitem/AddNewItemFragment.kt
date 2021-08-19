package com.example.rsandroidtask4.ui.addnewitem

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment

class AddNewItemFragment : Fragment() {

    companion object{


        fun newInstance() = AddNewItemFragment().apply {
            arguments = Bundle.EMPTY
        }
    }
}