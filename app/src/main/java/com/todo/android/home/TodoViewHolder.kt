package com.todo.android.home

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(data: ListData)
}