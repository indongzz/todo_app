package com.todo.android.home.dialog

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.findFragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.todo.android.TodoApplication
import com.todo.android.TodoRepository
import com.todo.android.databinding.HomeBottomSheetBinding
import com.todo.android.datebase.Item
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime
import org.threeten.bp.format.DateTimeFormatter

class HomeBottomSheetViewModel(
    val timeFormatter: String,
    val repository: TodoRepository
) : ViewModel() {
    var localDate: LocalDate = LocalDate.now()
    var localTime: LocalTime = LocalTime.now()
    var dateValue =
        MutableLiveData(localDate.format(DateTimeFormatter.ofPattern(Item.getBestDateTimePattern())))
    var timeValue =
        MutableLiveData(localTime.format(DateTimeFormatter.ofPattern(timeFormatter)))

    fun onDateChipClicked(v: View) = DatePickerDialog(v.context, { view, year, month, dayOfMonth ->
        val pattern = Item.getBestDateTimePattern(isSameYear = LocalDate.now().year == year)
        LocalDate.of(year, month + 1, dayOfMonth).also { localDate = it } // java Calendar backport
            .format(DateTimeFormatter.ofPattern(pattern))
            .also { dateValue.value = it }
    }, localDate.year, localDate.monthValue - 1, localDate.dayOfMonth).show()

    fun onTimeChipClicked(v: View) = TimePickerDialog(v.context, { view, hour, min ->
        LocalTime.of(hour, min).also { localTime = it }
            .format(DateTimeFormatter.ofPattern(timeFormatter))
            .also { timeValue.value = it }
    }, localTime.hour, localTime.minute, false).show()

    fun submit(v: View) {
        repository.insert(Item.ContentEntity(
            check = false,
            title = "temp",
            baseDate = localDate,
            time = localTime))
        v.findFragment<HomeBottomSheetFragment>().dismissAllowingStateLoss()
    }

    class Factory(context: Context) : ViewModelProvider.Factory {
        val formatter = Item.getProperTimeAsClockPreference(context)
        val repository = (context as TodoApplication).repository

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return HomeBottomSheetViewModel(formatter, repository) as T
        }
    }
}