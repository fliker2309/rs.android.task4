package com.example.rsandroidtask4.ui.itemlist.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.rsandroidtask4.data.db.entity.Employee

class ItemAdapter : ListAdapter<Employee,ItemViewHolder>(ItemDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
        ItemViewHolder.create(parent)

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) =
        holder.bind(getItem(position))
}



