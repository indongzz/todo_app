package com.todo.android

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.todo.android.datebase.TodoDatabase

class TodoApplication : Application() {
    val database by lazy { TodoDatabase.getDatabase(this) }
    val repository by lazy { TodoRepository(database.todoDao()) }

    init {
        AndroidThreeTen.init(this)
    }
}