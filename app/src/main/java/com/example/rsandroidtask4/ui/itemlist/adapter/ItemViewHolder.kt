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

    companion object {
        fun create(parent: ViewGroup): ItemViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ViewHolderItemBinding.inflate(inflater, parent, false)
            return ItemViewHolder(binding)
        }
    }

    fun bind(item: Item) {
        binding.apply {
            ageTv.text = item.age.toString()
            nameTv.text = item.name
            breedTv.text = item.breed
        }
    }
}