package com.todo.android.home.main

import androidx.lifecycle.*
import com.todo.android.TodoRepository
import com.todo.android.datebase.Item

class MainListViewModel(private val repository: TodoRepository) : ViewModel() {

    var contentList: LiveData<List<Item.ContentEntity>> = repository.todoDao.findAll()
    var resultList: LiveData<List<Item>> = Transformations.switchMap(contentList) {
        combineContentAndDate(it, repository.todoDao.getBaseDateAll())
    }

    private fun combineContentAndDate(
        contentList: List<Item.ContentEntity>?,
        calenderList: List<Item.Calender>
    ) : LiveData<List<Item>> {
        val list = mutableListOf<Item>()
        contentList?.let {
            it.groupBy { entity -> entity.baseDate }.map { entry ->
                calenderList.find { c -> c.date == entry.key }
                    ?.let { calender -> list.add(calender) }
                list.addAll(entry.value)
            }
        }
        return MutableLiveData(list.toList())
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