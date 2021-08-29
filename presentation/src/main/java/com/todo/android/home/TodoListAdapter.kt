package com.todo.android.home

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.todo.android.R
import com.todo.android.databinding.DateContentBinding
import com.todo.android.databinding.TodoContentBinding
import com.todo.android.database.Item
import kotlin.math.roundToInt

class TodoListAdapter(val callback: OnItemCallback, val lifecycleOwner: LifecycleOwner) :
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
                TodoViewHolder.ContentViewHolder(binding, callback, lifecycleOwner)
            } else -> {
                val binding =
                    DateContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                TodoViewHolder.DateViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(getItem(position), position == 0)
    }

    override fun getItemViewType(position: Int): Int = when(getItem(position)) {
        is Item.ContentEntity -> TODO_CONTENT
        is Item.Calendar -> DATE_CONTENT
    }

    class DividerItemDecoration(val context: Context) : RecyclerView.ItemDecoration() {
        private var divider: Drawable?
        private val bounds = Rect()

        init {
            val attrs = intArrayOf(android.R.attr.listDivider)
            val ta = context.applicationContext.obtainStyledAttributes(attrs)
            divider = ta.getDrawable(0)
            ta.recycle()
        }

        override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
            c.save()
            divider?.let {
                val childCount = parent.childCount
                for (i in 0 until childCount) {
                    var left = parent.paddingLeft
                    var right = parent.width - parent.paddingRight
                    val margin =
                        context.resources.getDimensionPixelSize(R.dimen.header_padding_15)
                    val child = parent.getChildAt(i)

                    when (parent.getChildViewHolder(child)) {
                        is TodoViewHolder.DateViewHolder -> {}
                        is TodoViewHolder.ContentViewHolder -> {
                            left += margin
                            right -= margin
                        }
                    }
                    parent.getDecoratedBoundsWithMargins(child, bounds)
                    val bottom = bounds.bottom + child.translationY.roundToInt()
                    val top = bottom - it.intrinsicHeight

                    it.setBounds(left, top, right, bottom)
                    it.draw(c)
                }
            }
            c.restore()
        }

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            val bottom = divider?.run { intrinsicHeight } ?: 0
            outRect.set(0, 0, 0, bottom)
        }
    }
}