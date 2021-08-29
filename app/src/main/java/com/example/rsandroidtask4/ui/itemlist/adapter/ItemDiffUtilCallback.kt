package com.example.rsandroidtask4.ui.itemlist.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.rsandroidtask4.data.db.entity.Employee

class ItemDiffUtilCallback : DiffUtil.ItemCallback<Employee>() {
    override fun areItemsTheSame(oldEmployee: Employee, newEmployee: Employee): Boolean = oldEmployee == newEmployee

    override fun areContentsTheSame(oldEmployee: Employee, newEmployee: Employee): Boolean {
        return oldEmployee.id == newEmployee.id && oldEmployee.name == newEmployee.name && oldEmployee.age == newEmployee.age && oldEmployee.position == newEmployee.position
    }

}