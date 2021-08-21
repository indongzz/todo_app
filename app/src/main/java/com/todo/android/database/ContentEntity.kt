package com.todo.android.database

import android.annotation.SuppressLint
import android.content.Context
import android.text.format.DateFormat
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.todo.android.home.TodoViewHolder
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.TextStyle
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

sealed class Item {
    // temp
    companion object {
        const val yearlessFormat = "MMMM d"

        @SuppressLint("SimpleDateFormat")
        fun getBestDateTimePattern(
            locale: Locale = Locale.getDefault(),
            isSameYear: Boolean = true
        ): String {
            return if (isSameYear) SimpleDateFormat(
                DateFormat.getBestDateTimePattern(locale, yearlessFormat)).toPattern()
            else (java.text.DateFormat.getDateInstance(
                java.text.DateFormat.LONG, locale) as SimpleDateFormat).toPattern()
        }

        fun getProperTimeAsClockPreference(context: Context): String =
            (DateFormat.getTimeFormat(context) as SimpleDateFormat).toPattern()
    }

    @Entity(tableName = "todolist")
    data class ContentEntity(
        @PrimaryKey(autoGenerate = true)
        var id: Long = 0,
        var check: Boolean,
        var title: String,
        var baseDate: LocalDate,
        var time: LocalTime
    ) : Item(), Serializable {

    }

    data class Calendar(
        @ColumnInfo(name = "baseDate")
        val date: LocalDate,
    ) : Item() {

        fun makeDateText(): String {
            var prefix = ""
            when (date.compareTo(LocalDate.now())) {
                TodoViewHolder.DateViewHolder.TODAY -> prefix = "오늘  ·  "
                TodoViewHolder.DateViewHolder.TOMORROW -> prefix = "내일  ·  "
                in Int.MIN_VALUE..TodoViewHolder.DateViewHolder.PAST -> return "기한이 지난"
            }

            val dayOfWeekStr = " (" + date.dayOfWeek.getDisplayName(
                TextStyle.SHORT,
                Locale.getDefault()) + ")"
            return prefix + date.format(
                DateTimeFormatter.ofPattern(
                    getBestDateTimePattern(
                        isSameYear = LocalDate.now().year == date.year
                    )
                )
            ) + dayOfWeekStr
        }
    }
}


