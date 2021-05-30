package com.todo.android.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.todo.android.databinding.DateContentBinding
import com.todo.android.databinding.TodoContentBinding

class TodoListAdapter(var mData: ArrayList<Item>) :
    RecyclerView.Adapter<TodoViewHolder>() {

    companion object {
        const val TODO_CONTENT = 0
        const val DATE_CONTENT = 1
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
        holder.bind(mData[position])
    }

    override fun getItemCount(): Int = mData.size

    override fun getItemViewType(position: Int): Int = mData[position].viewType


}