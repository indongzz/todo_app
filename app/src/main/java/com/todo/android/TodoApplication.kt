package com.todo.android

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.todo.android.datebase.TodoDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class TodoApplication : Application() {
    val database by lazy { TodoDatabase.getDatabase(this) }
    val repository by lazy { TodoRepository(database.todoDao()) }

    init {
        AndroidThreeTen.init(this)
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@TodoApplication)
        }
    }
}