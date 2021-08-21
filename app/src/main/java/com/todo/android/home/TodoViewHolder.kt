package com.todo.android.home

import android.view.View
import androidx.core.view.setMargins
import androidx.core.view.updateLayoutParams
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.todo.android.R
import com.todo.android.databinding.DateContentBinding
import com.todo.android.databinding.TodoContentBinding
import com.todo.android.database.Item
import org.threeten.bp.LocalDate
import java.util.*

sealed class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: Item, isFirst: Boolean)

    class ContentViewHolder(
        val binding: TodoContentBinding,
        callback: OnItemCallback,
        lifecycleOwner: LifecycleOwner,
    ) : TodoViewHolder(binding.root) {
        var checked = binding.completedBox

        init {
            binding.callback = callback
            binding.lifecycleOwner = lifecycleOwner
        }

        override fun bind(item: Item, isFirst: Boolean) {
            if (item is Item.ContentEntity) {
                binding.content = item
            }
        }
    }

    class DateViewHolder(val binding: DateContentBinding) : TodoViewHolder(binding.root) {
        val now: LocalDate = LocalDate.now()
        val dateTime = binding.datetime

        companion object {
            const val PAST = -1
            const val TODAY = 0
            const val TOMORROW = 1
            const val THE_OTHER_FUTURE = 2
        }

        override fun bind(item: Item, isFirst: Boolean) {
            if (item is Item.Calendar) {
                binding.calendar = item
                val resources = itemView.resources

                // set margin
                if (!isFirst) {
                    itemView.updateLayoutParams<RecyclerView.LayoutParams> {
                        topMargin = resources.getDimensionPixelSize(R.dimen.header_padding_15)
                    }
                } else {
                    itemView.updateLayoutParams<RecyclerView.LayoutParams> {
                        setMargins(resources.getDimensionPixelSize(R.dimen.header_padding_5))
                    }
                }
            }
        }
    }
}
