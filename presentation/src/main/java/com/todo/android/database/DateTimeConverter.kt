package com.todo.android.database

import androidx.room.TypeConverter
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime

class DateTimeConverter {
    @TypeConverter
    fun toDateTime(str: String?): LocalTime? = str?.let { LocalTime.parse(it) }

    @TypeConverter
    fun toDateTimeString(date: LocalTime?): String = date.toString()

    @TypeConverter
    fun toDate(str: String?): LocalDate? = str?.let { LocalDate.parse(it) }

    @TypeConverter
    fun toDateString(date: LocalDate?): String = date.toString()
}