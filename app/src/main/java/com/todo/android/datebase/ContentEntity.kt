package com.todo.android.datebase

import android.annotation.SuppressLint
import android.content.Context
import android.text.format.DateFormat
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import java.text.SimpleDateFormat
import java.util.*


sealed class Item {
    @Entity(tableName = "todolist")
    data class ContentEntity(
        @PrimaryKey(autoGenerate = true)
        var id: Long,
        var title: String,
        var baseDate: LocalDate,
        var startTime: LocalDateTime,
        var endTime: LocalDateTime,
    ) : Item()

    // temp
    companion object {
        const val yearlessFormat = "MMMM d"
        @SuppressLint("SimpleDateFormat")
        fun getBestDateTimePattern(locale: Locale, isSameYear: Boolean = true): SimpleDateFormat {
            return if (isSameYear) SimpleDateFormat(
                DateFormat.getBestDateTimePattern(
                    locale,
                    yearlessFormat
                )
            ) else java.text.DateFormat.getDateInstance(
                java.text.DateFormat.LONG,
                locale
            ) as SimpleDateFormat
        }

        fun getProperTimeAsClockPreference(context: Context): SimpleDateFormat =
            DateFormat.getTimeFormat(context) as SimpleDateFormat
    }

    data class Calender(
        @ColumnInfo(name = "baseDate")
        val date: LocalDate,
    ) : Item()
}


