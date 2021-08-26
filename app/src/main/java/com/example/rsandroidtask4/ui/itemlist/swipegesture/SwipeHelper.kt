package com.example.rsandroidtask4.ui.itemlist.swipegesture


import android.content.Context
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.rsandroidtask4.data.db.entity.Item

class SwipeHelper(onSwiped: (Item) -> Unit,context: Context) : ItemTouchHelper(SwipeCallback(context,onSwiped))