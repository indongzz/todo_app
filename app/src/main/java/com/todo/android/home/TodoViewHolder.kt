package com.todo.android.home

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.todo.android.databinding.DateContentBinding
import com.todo.android.databinding.TodoContentBinding
import com.todo.android.datebase.Item
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.TextStyle
import java.util.*

abstract class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: Item)
}

class ViewHolderContent(binding: TodoContentBinding) : TodoViewHolder(binding.root) {
    var title = binding.title
    var checked = binding.completedBox

    override fun bind(item: Item) {
        if (item is Item.ContentEntity) {
            title.text = item.title
        }

    }
}
class ViewHolderDate(binding: DateContentBinding) : TodoViewHolder(binding.root) {
    var dateTime = binding.datetime

    override fun bind(item: Item) {
        if (item is Item.Calendar) {
            val localDate = item.date
            val dayOfWeekStr = " (" + localDate.dayOfWeek.getDisplayName(TextStyle.SHORT,
                Locale.getDefault()) + ")"
            val str = localDate.format(DateTimeFormatter.ofPattern(Item.getBestDateTimePattern(
                isSameYear = LocalDate.now().year == localDate.year))) + dayOfWeekStr

            dateTime.text = str
        }
    }
}