package com.example.todolist

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        todoAdapter = TodoAdapter(mutableListOf())
        rvTodoItems.adapter = todoAdapter
        rvTodoItems.layoutManager = LinearLayoutManager(this)

        loadData()

        btnAddTodo.setOnClickListener {
            saveTodo()
        }

        btnDeleteDoneTodos.setOnClickListener {
            deleteData()
        }
    }

    private fun saveTodo() {
        val todoTitle = etTodoTitle.text.toString()
        if(todoTitle.isNotEmpty()) {
            val todo = Todo(todoTitle)

            todoAdapter.addTodo(todo)
            etTodoTitle.text.clear()

            val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.apply {
                putString("STRING_KEY", todoTitle)
            }.apply()

//            Отладка
            Toast.makeText(this, "Data save " + todo, Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadData() {
        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val savedString = sharedPreferences.getString("STRING_KEY", null)

        val todo = Todo(savedString.toString())
        Toast.makeText(this, "Load data " + todo, Toast.LENGTH_SHORT).show()
        todoAdapter.addTodo(todo)
    }

    private fun deleteData() {
        todoAdapter.deleteDoneTodo()
    }

}

//    private fun saveData() {
//        val todoTitle = etTodoTitle.text.toString()
//        if(todoTitle.isNotEmpty()) {
//            val todo = Todo(todoTitle)
//
//            val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
//            val editor = sharedPreferences.edit()
//
//            editor.apply {
//                putString("STRING_KEY", todo)
//            }
//
////            todoAdapter.addTodo(todo)
//            etTodoTitle.text.clear()
//        }
//
//    }
//
//    private fun loadData() {
//
//        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
//        val savedString = sharedPreferences.getString("STRING_KEY", null)
//        val todoTitle = savedString
//        val todo = Todo(todoTitle.toString())
//
//        todoAdapter = TodoAdapter(mutableListOf())
//
//        todoAdapter.addTodo(todo)
//
//    }

//private fun saveData() {
//
//    todoAdapter = TodoAdapter(mutableListOf())
//    rvTodoItems.adapter = todoAdapter
//    rvTodoItems.layoutManager = LinearLayoutManager(this)
//
//    val todoTitle = etTodoTitle.text.toString()
//    if(todoTitle.isNotEmpty()) {
//        val todo = Todo(todoTitle)
//
//        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
//        val editor = sharedPreferences.edit()
//
//        editor.apply {
//            putString("STRING_KEY", todoTitle)
//        }.apply()
//
//        todoAdapter.addTodo(todo)
//        etTodoTitle.text.clear()
//
//        Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show()
//    }
//
//}

//            saveData()
//
//
//override fun onCreate(savedInstanceState: Bundle?) {
//    super.onCreate(savedInstanceState)
//    setContentView(R.layout.activity_main)
//
//    loadData()
//
//    btnAddTodo.setOnClickListener {
//        saveData()
//    }
//
//    btnDeleteDoneTodos.setOnClickListener {
//        deleteData()
//    }
//}
//
//private fun saveData() {
//
//    todoAdapter = TodoAdapter(mutableListOf())
//    rvTodoItems.adapter = todoAdapter
//    rvTodoItems.layoutManager = LinearLayoutManager(this)
//
//    val todoTitle = etTodoTitle.text.toString()
//    if(todoTitle.isNotEmpty()) {
//        val todo = Todo(todoTitle)
//
//        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
//        val editor = sharedPreferences.edit()
//
//        editor.apply {
//            putString("STRING_KEY", todoTitle)
//        }.apply()
//
//        todoAdapter.addTodo(todo)
//        etTodoTitle.text.clear()
//
//        Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show()
//    }
//
//}
//
//private fun loadData() {
//
//    val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
//    val savedString = sharedPreferences.getString("STRING_KEY", null)
//
//    todoAdapter = TodoAdapter(mutableListOf())
//    rvTodoItems.adapter = todoAdapter
//    rvTodoItems.layoutManager = LinearLayoutManager(savedString)
//
//}
//
//private fun deleteData() {
//
//    todoAdapter = TodoAdapter(mutableListOf())
//    rvTodoItems.adapter = todoAdapter
//    rvTodoItems.layoutManager = LinearLayoutManager(this)
//
//    val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
//    val savedString = sharedPreferences.getString("STRING_KEY", null)
//
//    todoAdapter.deleteDoneTodo()
//
//}