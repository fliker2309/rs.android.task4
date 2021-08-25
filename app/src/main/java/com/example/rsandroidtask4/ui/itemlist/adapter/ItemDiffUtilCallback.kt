package com.example.rsandroidtask4.ui.itemlist.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.rsandroidtask4.data.db.entity.Item

class ItemDiffUtilCallback : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.id == newItem.id && oldItem.name == newItem.name && oldItem.age == newItem.age && oldItem.breed == newItem.breed
    }

}