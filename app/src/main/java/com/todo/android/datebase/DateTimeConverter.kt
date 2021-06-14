package com.todo.android.datebase

import androidx.room.TypeConverter
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime

class DateTimeConverter {
    @TypeConverter
    fun toDateTime(str: String?): LocalDateTime? = str?.let { LocalDateTime.parse(it) }

    @TypeConverter
    fun toDateTimeString(date: LocalDateTime?): String = date.toString()

    @TypeConverter
    fun toDate(str: String?): LocalDate? = str?.let { LocalDate.parse(it) }

    @TypeConverter
    fun toDateString(date: LocalDate?): String = date.toString()
}