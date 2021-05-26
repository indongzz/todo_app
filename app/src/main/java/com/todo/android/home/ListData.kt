package com.todo.android.home

import java.time.LocalDate
import java.time.LocalTime

abstract class ListData(
    open var viewType: Int
)

data class TodoContent(
    var checked: Boolean,
    var title: String = "",
    var startTime: LocalTime,
    var endTime: LocalTime,
    override var viewType: Int
) : ListData(viewType)

data class TodoDate(var date: LocalDate, override var viewType: Int) : ListData(viewType)