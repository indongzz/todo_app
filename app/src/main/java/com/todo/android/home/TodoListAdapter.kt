package com.todo.android.home

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
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
        is Item.Calendar -> DATE_CONTENT
    }

    class DividerItemDecoration(context: Context) : RecyclerView.ItemDecoration() {
        private var _divider: Drawable?
        private val divider: Drawable
        get() = _divider!!

        init {
            val attrs = intArrayOf(android.R.attr.listDivider)
            val ta = context.applicationContext.obtainStyledAttributes(attrs)
            _divider = ta.getDrawable(0)
            ta.recycle()
        }

        override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
            val left = parent.paddingLeft
            val right = parent.width - parent.paddingRight

            val childCount = parent.childCount
            for (i in 0..childCount) {
                val child = parent.getChildAt(i)
                val params = child.layoutParams as RecyclerView.LayoutParams

                val top = child.bottom + params.bottomMargin
                val bottom = top + divider.intrinsicHeight

                divider.setBounds(left, top, right, bottom)
                divider.draw(c)
            }
        }

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State,
        ) {
            super.getItemOffsets(outRect, view, parent, state)
        }
    }
}