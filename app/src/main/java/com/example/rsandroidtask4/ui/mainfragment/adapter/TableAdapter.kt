package com.example.rsandroidtask4.ui.mainfragment.adapter

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class TableAdapter : ListAdapter<DataItem,RecyclerView.ViewHolder>(TableDiffUtilCallback()){
}