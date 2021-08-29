package com.todo.android.home.main

import androidx.lifecycle.*
import com.todo.android.TodoRepository
import com.todo.android.database.Item
import com.todo.android.home.OnItemCallback
import org.threeten.bp.LocalDate

class MainViewModel(private val repository: TodoRepository) : ViewModel(), OnItemCallback {
    var contentList: LiveData<List<Item.ContentEntity>> = repository.findPendingJobs()
    var combineList = Transformations.switchMap(contentList) {
        val list = mutableListOf<Item>()
        val now = LocalDate.now()
        var isBeforeDateExist = false

        it.groupBy { entity -> entity.baseDate }.map { entry ->
            if (now.isAfter(entry.key) && !isBeforeDateExist) {
                isBeforeDateExist = true
                list.add(Item.Calendar(LocalDate.MIN))
            } else if (!now.isAfter(entry.key)) {
                list.add(Item.Calendar(entry.key))
            }
            list.addAll(entry.value)
        }
        MutableLiveData(list.toList())
    }

    val onClickedItem = MutableLiveData<Item.ContentEntity>()

    fun insert(content: Item.ContentEntity) {
        repository.insert(content)
    }

    override fun onClickedCheckBox(item: Item.ContentEntity) {
        item.check = true
        repository.update(item)
    }

    override fun onClickedItem(item: Item.ContentEntity) {
        onClickedItem.value = item
    }

    class Factory(private val repository: TodoRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainViewModel(repository) as T
        }
    }
}