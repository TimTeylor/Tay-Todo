package com.example.todolist

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_todo.*
import java.nio.file.Files.delete

class DoneActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        btnDoneTodo.setOnClickListener {
            println("SHOW CLICK DONE")
        }
    }
}

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

        val data = db.readData()

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
                db.insertData(todoTitle)
                etTodoTitle.text.clear()
            }
        }

//        btnDone.setOnClickListener {
//            println("show db data ")
//            db.delete()
//            todoAdapter.deleteDoneTodo()
//        }
    }
}