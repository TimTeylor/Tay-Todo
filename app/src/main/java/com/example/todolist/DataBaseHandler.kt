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

    fun readData(): MutableList<Todo> {
        val list: MutableList<Todo> = ArrayList()
        val db = this.readableDatabase
        val query = "Select * from $TABLENAME"
        val result = db.rawQuery(query, null)
        if(result.moveToFirst()) {
            do {
                val todoId = result.getInt(result.getColumnIndex(COL_ID))
                val todoTitle = result.getString(1)
                val todo = Todo(todoTitle, todoId)
                list.add(todo)
            } while (result.moveToNext())
        }
        return list
    }

//    fun delete(String: ID) {
//        val database = this.writableDatabase
//        database.delete(TABLENAME, "ID=" + ID, null)
//    }

    fun insertData(title: String) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_NAME, title)
        val result = database.insert(TABLENAME, null, contentValues)
        if(result == (0).toLong()) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
        }
    }
}