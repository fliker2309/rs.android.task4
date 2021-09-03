package com.example.rsandroidtask4.ui.fragments.list.swipegesture


import android.content.Context
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.rsandroidtask4.data.db.entity.Employee

class SwipeHelper(onSwiped: (Employee) -> Unit, context: Context) : ItemTouchHelper(SwipeCallback(context,onSwiped))