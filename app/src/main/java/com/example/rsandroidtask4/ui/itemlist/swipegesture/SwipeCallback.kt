package com.example.rsandroidtask4.ui.itemlist.swipegesture

import android.graphics.Canvas
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import android.content.Context
import androidx.core.content.ContextCompat
import com.example.rsandroidtask4.R
import com.example.rsandroidtask4.data.db.entity.Item
import com.example.rsandroidtask4.ui.itemlist.adapter.ItemViewHolder
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator

class SwipeCallback(
    context: Context,
    private val onSwiped: (Item) -> Unit,

    ) : ItemTouchHelper.SimpleCallback(
    0,
    ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
) {


    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        (viewHolder as? ItemViewHolder)?.item?.let { onSwiped(it) }
    }

       private val deleteColor = ContextCompat.getColor(context, R.color.red)
    private val deleteIcon = R.drawable.ic_delete

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

       override fun onChildDraw(
           c: Canvas,
           recyclerView: RecyclerView,
           viewHolder: RecyclerView.ViewHolder,
           dX: Float,
           dY: Float,
           actionState: Int,
           isCurrentlyActive: Boolean
       ) {
           RecyclerViewSwipeDecorator.Builder(
               c,
               recyclerView,
               viewHolder,
               dX,
               dY,
               actionState,
               isCurrentlyActive
           )
                  .addSwipeLeftBackgroundColor(deleteColor)
               .addBackgroundColor(deleteColor)
               .addSwipeLeftActionIcon(deleteIcon)
               .addSwipeRightBackgroundColor(deleteColor)
               .addSwipeRightActionIcon(deleteIcon)
               .create()
               .decorate()
       }
}