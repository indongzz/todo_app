package com.todo.android.home

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.todo.android.databinding.DateContentBinding
import com.todo.android.databinding.TodoContentBinding
import com.todo.android.datebase.Item
import org.threeten.bp.LocalDateTime; // backport

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
    var date = binding.datetime

    override fun bind(item: Item) {
        if (item is Item.Calender) {
            /*date.text = item.getBestDateTimePattern(
                Locale.getDefault(),
                LocalDateTime.now().year == item.startTime.year
            ).format(date)*/
        }
    }
}