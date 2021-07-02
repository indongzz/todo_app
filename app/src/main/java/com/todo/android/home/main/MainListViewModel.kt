package com.todo.android.home.main

import androidx.lifecycle.*
import com.todo.android.TodoRepository
import com.todo.android.datebase.Item
import org.threeten.bp.LocalDate

class MainListViewModel(private val repository: TodoRepository) : ViewModel() {

    var contentList: LiveData<List<Item.ContentEntity>> = repository.todoDao.findAll()
    var combineList: LiveData<List<Item>> = Transformations.switchMap(contentList) {
        val list = mutableListOf<Item>()
        val now = LocalDate.now()
        var isBeforeDateExist = false
        it.groupBy { entity -> entity.baseDate }.map { entry ->
            if (now.isBefore(entry.key) && !isBeforeDateExist) {
                isBeforeDateExist = true
                list.add(Item.Calendar(LocalDate.MIN))
            } else if (!now.isBefore(entry.key)) {
                list.add(Item.Calendar(entry.key))
            }
            list.addAll(entry.value)
        }
        MutableLiveData(list.toList())
    }

    fun insert(content: Item.ContentEntity) {
        repository.insert(content)
    }


    class Factory(private val repository: TodoRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainListViewModel(repository) as T
        }
    }
}