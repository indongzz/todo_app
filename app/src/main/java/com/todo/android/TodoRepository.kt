package com.todo.android

import com.todo.android.datebase.Item
import com.todo.android.datebase.TodoDao

class TodoRepository(var todoDao: TodoDao) {

    fun insert(item: Item.ContentEntity) {
        todoDao.insert(item)
    }
}