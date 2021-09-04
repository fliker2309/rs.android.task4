package com.example.rsandroidtask4.ui.fragments.list.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.rsandroidtask4.data.db.entity.Employee

class EmployeeAdapter : ListAdapter<Employee, EmployeeViewHolder>(EmployeeDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder =
        EmployeeViewHolder.create(parent)

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(getItem(position))

    }

}



