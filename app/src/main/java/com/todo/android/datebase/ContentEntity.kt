package com.todo.android.datebase

import android.annotation.SuppressLint
import android.content.Context
import android.text.format.DateFormat
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime
import java.text.SimpleDateFormat
import java.util.*


sealed class Item {
    @Entity(tableName = "todolist")
    data class ContentEntity(
        @PrimaryKey(autoGenerate = true)
        var id: Long = 0,
        var check: Boolean,
        var title: String,
        var baseDate: LocalDate,
        var time: LocalTime
    ) : Item()

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

    data class Calendar(
        @ColumnInfo(name = "baseDate")
        val date: LocalDate,
    ) : Item()
}


