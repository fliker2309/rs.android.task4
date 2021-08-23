package com.example.rsandroidtask4.ui.itemlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rsandroidtask4.data.db.entity.Item
import com.example.rsandroidtask4.databinding.ViewHolderItemBinding

class ItemViewHolder(
    private val binding: ViewHolderItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    var item: Item? = null
        private set

    fun bind(item: Item) {
        this.item = item

        views {
            ageTv.text = item.age
            nameTv.text = item.name
            breedTv.text = item.breed
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