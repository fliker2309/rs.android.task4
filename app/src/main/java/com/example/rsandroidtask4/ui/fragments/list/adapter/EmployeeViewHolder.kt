package com.example.rsandroidtask4.ui.fragments.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rsandroidtask4.data.db.entity.Employee
import com.example.rsandroidtask4.databinding.ViewHolderItemBinding

class EmployeeViewHolder(
    private val binding: ViewHolderItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    var employee: Employee? = null
        private set

    fun bind(employee: Employee) {
        this.employee = employee

        views {
            ageTv.text = employee.age
            nameTv.text = employee.name
            positionTv.text = employee.position
            surnameTv.text = employee.surname
            experienceTv.text = employee.experience
        }
    }


    private fun <T> views(block: ViewHolderItemBinding.() -> T): T? = binding.block()

    companion object {
        fun create(parent: ViewGroup): EmployeeViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ViewHolderItemBinding.inflate(inflater, parent, false)
            return EmployeeViewHolder(binding)
        }
    }
}