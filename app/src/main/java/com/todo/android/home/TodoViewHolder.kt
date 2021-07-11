package com.todo.android.home

import android.view.View
import androidx.core.view.setMargins
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import com.todo.android.R
import com.todo.android.databinding.DateContentBinding
import com.todo.android.databinding.TodoContentBinding
import com.todo.android.datebase.Item
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.TextStyle
import java.util.*

sealed class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: Item, isFirst: Boolean)

    class ViewHolderContent(binding: TodoContentBinding) : TodoViewHolder(binding.root) {
        var title = binding.title
        var checked = binding.completedBox

        override fun bind(item: Item, isFirst: Boolean) {
            if (item is Item.ContentEntity) {
                title.text = item.title
            }

        }
    }

    class ViewHolderDate(val binding: DateContentBinding) : TodoViewHolder(binding.root) {
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
                val str = makeDateText(item.date)
                val resources = itemView.resources
                dateTime.text = str

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

        private fun makeDateText(localDate: LocalDate): String {
            var prefix = ""
            when (localDate.compareTo(now)) {
                TODAY -> {
                    prefix = "오늘  ·  "
                }
                TOMORROW -> {
                    prefix = "내일  ·  "
                }
                in Int.MIN_VALUE..PAST -> {
                    return "기한이 지난"
                }
            }

            val dayOfWeekStr = " (" + localDate.dayOfWeek.getDisplayName(
                TextStyle.SHORT,
                Locale.getDefault()
            ) + ")"
            return prefix + localDate.format(
                DateTimeFormatter.ofPattern(
                    Item.getBestDateTimePattern(
                        isSameYear = LocalDate.now().year == localDate.year
                    )
                )
            ) + dayOfWeekStr
        }
    }
}
