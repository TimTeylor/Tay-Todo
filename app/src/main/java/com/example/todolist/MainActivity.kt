package com.example.todolist

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_todo.*

class MainActivity : AppCompatActivity() {

    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        todoAdapter = TodoAdapter(mutableListOf())

        rvTodoItems.adapter = todoAdapter
        rvTodoItems.layoutManager = LinearLayoutManager(this)

        val context = this
        val db = DataBaseHandler(context)
//        Toast.makeText(null, "Start", Toast.LENGTH_SHORT).show()

        val data = db.readData()
//        Toast.makeText(this, "Data read" + data, Toast.LENGTH_SHORT).show()
        for (i in 0 until data.size) {
            val todo = Todo(data[i].title, data[i].id)
            todoAdapter.addTodo(todo)
        }

        btnAddTodo.setOnClickListener {
            val todoTitle = etTodoTitle.text.toString()
            if(todoTitle.isNotEmpty()) {
                val lastId = data.size
                val todo = Todo(todoTitle, lastId)

                todoAdapter.addTodo(todo)
                db.insertData(todo)
                etTodoTitle.text.clear()
            }
        }

        btnDone.setOnClickListener {
            val todoTitle = etTodoTitle.text.toString()
            val lastId = data.size
            val todo = Todo(todoTitle, lastId)
            todoAdapter.deleteDoneTodo(todo)
        }
    }
}