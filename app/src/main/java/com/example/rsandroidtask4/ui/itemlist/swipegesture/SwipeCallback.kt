package com.example.rsandroidtask4.ui.itemlist.swipegesture

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import android.content.Context
import com.example.rsandroidtask4.data.db.entity.Employee
import com.example.rsandroidtask4.ui.itemlist.adapter.ItemViewHolder

class SwipeCallback(
    context: Context,
    private val onSwiped: (Employee) -> Unit,

    ) : ItemTouchHelper.SimpleCallback(
    0,
    ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
) {
    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        (viewHolder as? ItemViewHolder)?.employee?.let { onSwiped(it) }
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

}