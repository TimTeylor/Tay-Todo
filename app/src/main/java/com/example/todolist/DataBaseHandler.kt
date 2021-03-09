package com.example.todolist

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

val DATABASENAME = "TEY TODO DB"
val TABLENAME = "Todos"
val COL_NAME = "title"
val COL_ID = "id"

class DataBaseHandler(var context: Context) : SQLiteOpenHelper(context, DATABASENAME, null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE $TABLENAME ($COL_ID INTEGER PRIMARY KEY AUTOINCREMENT,$COL_NAME VARCHAR(256))"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //onCreate(db)
    }

    fun insertData(todo: Todo) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_NAME, todo.title)
        val result = database.insert(TABLENAME, null, contentValues)
        if(result == (0).toLong()) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
        }
    }

    fun readData(): MutableList<Todo> {
        val list: MutableList<Todo> = ArrayList()
        val db = this.readableDatabase
        val query = "Select * from $TABLENAME"
        val result = db.rawQuery(query, null)
        if(result.moveToFirst()) {
            do {
                val todoTitle = result.getString(result.getColumnIndex(COL_ID))
                val todoId = result.getInt(result.getColumnIndex(COL_NAME))
                val todo = Todo(todoTitle, todoId)
                list.add(todo)
            } while (result.moveToNext())
        }
        return list
    }
}