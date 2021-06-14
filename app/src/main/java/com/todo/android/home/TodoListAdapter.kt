package com.todo.android.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.todo.android.databinding.DateContentBinding
import com.todo.android.databinding.TodoContentBinding
import com.todo.android.datebase.Item

class TodoListAdapter :
    ListAdapter<Item, TodoViewHolder>(COMPARATOR) {

    companion object {
        const val TODO_CONTENT = 0
        const val DATE_CONTENT = 1

        private val COMPARATOR = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return when (viewType) {
            TODO_CONTENT -> {
                val binding =
                    TodoContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ViewHolderContent(binding)
            } else -> {
                val binding =
                    DateContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ViewHolderDate(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int = when(getItem(position)) {
        is Item.ContentEntity -> TODO_CONTENT
        is Item.Calender -> DATE_CONTENT
    }


}