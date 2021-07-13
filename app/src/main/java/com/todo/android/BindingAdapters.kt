package com.todo.android

import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.todo.android.datebase.Item

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("item")
    fun <T> setItem(recyclerView: RecyclerView, list: LiveData<T>) {
        val adapter = recyclerView.adapter
    }
}

fun <VH : RecyclerView.ViewHolder, T>RecyclerView.Adapter<VH>.setItem(item: List<T>?) {
    item.run {
        notifyDataSetChanged()
    }
}