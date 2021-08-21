package com.example.rsandroidtask4.ui.itemlist.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.rsandroidtask4.data.db.entity.Item

class ItemDiffUtilCallback : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean = oldItem == newItem

}