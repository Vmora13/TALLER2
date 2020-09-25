package com.uan.todolist

import android.content.Context
import android.widget.ArrayAdapter

class ListViewAdapter(private val context: Context, private val tasksListItems: MutableList<String>) {

    public fun getAdapter(): ArrayAdapter<String>{
        return ArrayAdapter(
            context,
            R.layout.activity_list,
            R.id.activity_list,
            tasksListItems
        )
    }
}