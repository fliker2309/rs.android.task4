package com.example.rsandroidtask4.ui.itemlist

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.rsandroidtask4.R
import com.example.rsandroidtask4.databinding.ItemListBinding

private const val TAG = "myLog"

class ItemListFragment : Fragment() {

    private var _binding: ItemListBinding? = null
    private val binding: ItemListBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ItemListBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.filter, menu)
        Log.d(TAG, "menu was created")
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.sort_by_name) {
            Toast.makeText(
                requireContext(),
                "You clicked Sort by name",
                Toast.LENGTH_LONG
            ).show()
        }
        /*  when (item.itemId) {
              R.id.sort_by_name -> Log.d(TAG,"clicked sort by name") *//*Toast.makeText(
                context,
                "You clicked Sort by name",
                Toast.LENGTH_LONG
            ).show()*//*
            R.id.sort_by_age -> Toast.makeText(
                context,
                "You clicked Sort by age",
                Toast.LENGTH_LONG
            ).show()
            R.id.sort_by_breed -> Toast.makeText(
                context,
                "You clicked Sort by breed",
                Toast.LENGTH_LONG
            ).show()
        }*/
        return super.onOptionsItemSelected(item)
    }

    companion object {

        fun newInstance() = ItemListFragment().apply {
            arguments = Bundle.EMPTY
        }
    }
}