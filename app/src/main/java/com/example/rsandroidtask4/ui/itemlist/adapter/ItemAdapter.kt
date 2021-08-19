package com.example.rsandroidtask4.ui.itemlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rsandroidtask4.data.db.entity.Item
import com.example.rsandroidtask4.databinding.ViewHolderItemBinding

class ItemAdapter() : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    private var items: List<Item> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun updateItems(newItems: List<Item>) {
        items = newItems
        notifyDataSetChanged()
    }

    class ItemViewHolder private constructor(private val binding: ViewHolderItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): ItemViewHolder {
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

}
