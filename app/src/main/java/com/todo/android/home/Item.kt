package com.todo.android.home

import android.annotation.SuppressLint
import android.content.Context
import android.text.format.DateFormat
import java.text.SimpleDateFormat
import org.threeten.bp.LocalDateTime; // backport
import java.util.*

class Item(
    var completed: Boolean,
    var title: String = "",
    var startTime: LocalDateTime,
    var endTime: LocalDateTime,
    var viewType: Int) {

    companion object {
        const val yearlessFormat = "MMMM d"
    }

    @SuppressLint("SimpleDateFormat")
    fun getBestDateTimePattern(locale: Locale, isSameYear: Boolean = true): SimpleDateFormat {
        return if (isSameYear) SimpleDateFormat(
            DateFormat.getBestDateTimePattern(
                locale,
                yearlessFormat)
        ) else java.text.DateFormat.getDateInstance(
            java.text.DateFormat.LONG,
            locale
        ) as SimpleDateFormat
    }

    fun getProperTimeAsClockPreference(context: Context): SimpleDateFormat =
        DateFormat.getTimeFormat(context) as SimpleDateFormat
}
