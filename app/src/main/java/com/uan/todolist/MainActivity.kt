package com.uan.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tasksFile: String = "tasks.txt"
        val frw = FileReadWrite()
        val tasksListItems: MutableList<String> = getTasksList(frw = frw, tasksFileName = tasksFile)
        val btnAdd = findViewById<Button>(R.id.button_add)
        val listView = findViewById<ListView>(R.id.listView)

        val adapter = ListViewAdapter(this, tasksListItems).getAdapter()
        listView.adapter = adapter

        listView.onItemLongClickListener =
            AdapterView.OnItemLongClickListener { adapterView, view, position, l ->
                val msg = "You just deleted: " + adapter.getItem(position).toString()
                tasksListItems.removeAt(position)
                frw.writeTasks(tasksFile, tasksListItems, baseContext)

                adapter.notifyDataSetChanged()
                Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
                true
            }

        btnAdd.setOnClickListener {
            val newTask: String = newtask_input.text.toString()

            if (newTask.isNotEmpty()) {
                tasksListItems.add(newTask)
                frw.writeTasks(tasksFile, tasksListItems, baseContext)
                Toast.makeText(baseContext, "The task has been saved!", Toast.LENGTH_SHORT).show();

                adapter.notifyDataSetChanged()
                newtask_input.setText("")
            }else{
                Toast.makeText(baseContext, "Not a valid task, try something else!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private fun getTasksList(frw: FileReadWrite, tasksFileName: String):  MutableList<String>{
        var tasksListItems: MutableList<String>

        if ( File( baseContext.getFileStreamPath(tasksFileName).getAbsolutePath() ).exists()) {
            tasksListItems = frw.readTasks(tasksFileName, baseContext)
        }else {
            tasksListItems = mutableListOf()
        }
        return tasksListItems
    }
}