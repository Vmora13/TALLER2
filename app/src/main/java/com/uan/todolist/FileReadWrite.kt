package com.uan.todolist

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import java.io.OutputStreamWriter
import java.util.*

class FileReadWrite {

    public fun readTasks(tasksFile: String, bc: Context): MutableList<String>{
        val inputFile = Scanner(bc.openFileInput(tasksFile))
        var tasksList: MutableList<String> = mutableListOf()

        while (inputFile.hasNextLine())
            tasksList.add(inputFile.nextLine())

        return tasksList
    }

    public fun writeTasks(tasksFile: String, itemsList: MutableList<String>, bc: Context): Boolean{
        val ofo =  bc.openFileOutput(tasksFile, AppCompatActivity.MODE_PRIVATE)
        val osw = OutputStreamWriter(ofo)

        for ( task in itemsList )
            ofo.write("$task\n".toByteArray())

        osw.close()
        return true
    }
}