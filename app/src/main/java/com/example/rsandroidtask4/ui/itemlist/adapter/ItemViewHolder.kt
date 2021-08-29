package com.example.rsandroidtask4.ui.itemlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rsandroidtask4.data.db.entity.Employee
import com.example.rsandroidtask4.databinding.ViewHolderItemBinding

class ItemViewHolder(
    private val binding: ViewHolderItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    var employee: Employee? = null
        private set

    fun bind(employee: Employee) {
        this.employee = employee

        views {
            ageTv.text = employee.age
            nameTv.text = employee.name
            breedTv.text = employee.breed
        }
    }


    private fun <T> views(block: ViewHolderItemBinding.() -> T): T? = binding.block()

    companion object {
        fun create(parent: ViewGroup): ItemViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ViewHolderItemBinding.inflate(inflater, parent, false)
            return ItemViewHolder(binding)
        }
    }
}