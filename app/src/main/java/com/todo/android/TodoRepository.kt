package com.todo.android

import androidx.lifecycle.LiveData
import com.todo.android.database.Item
import com.todo.android.database.TodoDao

class TodoRepository(var todoDao: TodoDao) {
    fun insert(item: Item.ContentEntity) = todoDao.insert(item)
    fun update(item: Item.ContentEntity) = todoDao.update(item)
    fun findPendingJobs(): LiveData<List<Item.ContentEntity>> = todoDao.findPendingJobs()
    fun findCompleteJobs(): LiveData<List<Item.ContentEntity>> = todoDao.findCompleteJobs()
}