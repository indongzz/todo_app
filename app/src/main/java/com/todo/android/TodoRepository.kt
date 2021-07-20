package com.todo.android

import com.todo.android.database.Item
import com.todo.android.database.TodoDao

class TodoRepository(var todoDao: TodoDao) {

    fun insert(item: Item.ContentEntity) {
        todoDao.insert(item)
    }
}