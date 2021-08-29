package com.todo.android.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(version = 1, entities = [Item.ContentEntity::class], exportSchema = false)
@TypeConverters(DateTimeConverter::class)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao

    companion object {
        @Volatile
        private var INSTANCE: TodoDatabase? = null

        fun getDatabase(context: Context): TodoDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(context.applicationContext,
                    TodoDatabase::class.java,
                    "todo_database")
                    .fallbackToDestructiveMigration()
                    // temp allow ui thread
                    .allowMainThreadQueries().build().also { INSTANCE = it }
            }
        }
    }
}