package com.todo.android

import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("item")
    fun <T> setItem(recyclerView: RecyclerView, list: LiveData<List<T>>) {
        val adapter = recyclerView.adapter as ListAdapter<T, *>
        adapter.submitList(list.value)
    }
}