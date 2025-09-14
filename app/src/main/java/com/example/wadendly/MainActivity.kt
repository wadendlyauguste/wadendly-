package com.example.wadendly

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView



class MainActivity : AppCompatActivity() {

    val listOfTask = mutableListOf<String>()
    lateinit var adapter: TaskItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        val onLongClickListener = object : TaskItemAdapter.onLongClickListener {
            override fun onItemLongClicked(position: Int) {
                // 1. Remove the item from the list
                listOfTask.removeAt(position)
                // 2. notify the adapter that our data set has changed
                adapter.notifyDataSetChanged()

            }

        }


        // Let's detect when the user click add button
        //findViewById<Button>(R.id.button).setOnClickListener {
        // Code in here is going to be executed when the user clicks on a button
        // Log.i("Augustin","User click on button")
        // }

        listOfTask.add("Do landry")
        listOfTask.add("ho for a walk")


        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        adapter = TaskItemAdapter(listOfTask, onLongClickListener)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)


        // set up the button and input field, so that the user can enter a task and add to the list
        val inputTextField = findViewById<EditText>(R.id.addTaskField)
        //Get a reference to the button
        // And then set on onclicklistener

        findViewById<Button>(R.id.button).setOnClickListener {
            // 1. Grab the text the user has inputted into @+id/addTaskField
            val userInputtedTask = inputTextField.text.toString()

            // 2. Add the string to our list of task: listOfTask
            listOfTask.add(userInputtedTask)
            // notify the adapter that our data has been updated
            adapter.notifyItemInserted(listOfTask.size - 1)
            // 3. Reset text field
            inputTextField.setText(" ")
        }


    }


}