package com.todo.android.home.dialog

import android.content.Context
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.todo.android.SingleLiveEvent
import com.todo.android.TodoApplication
import com.todo.android.TodoRepository
import com.todo.android.database.Item
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime
import org.threeten.bp.format.DateTimeFormatter

class HomeBottomSheetViewModel(
    private val timeFormatter: String,
    private val repository: TodoRepository
) : ViewModel() {
    var chipDate = MutableLiveData(LocalDate.now())
    var chipTime = MutableLiveData(LocalTime.now())

    var dateString = Transformations.map(chipDate) {
        it ?: LocalDate.now()
        it.format(DateTimeFormatter.ofPattern(Item.getBestDateTimePattern(isSameYear = it.year == LocalDate.now().year)))
    }
    var timeString = Transformations.map(chipTime) {
        it ?: LocalTime.now()
        it.format(DateTimeFormatter.ofPattern(timeFormatter))
    }

    // for two-way
    val title = MutableLiveData<String>()

    // for submit event observer
    // TODO : using eventWrapper?
    val event: SingleLiveEvent<Int> = SingleLiveEvent()

    companion object {
        const val TIME_CHIP_CLICKED = 1
        const val DATE_CHIP_CLICKED = 2
        const val SUBMIT_CLICKED = 3
    }

    fun onTimeChipClicked() {
        event.value = TIME_CHIP_CLICKED
    }

    fun onDateChipClicked() {
        event.value = DATE_CHIP_CLICKED
    }

    fun submit() {
        repository.insert(
            Item.ContentEntity(
                check = false,
                title = title.value ?: "null",
                baseDate = chipDate.value ?: LocalDate.now(),
                time = chipTime.value ?: LocalTime.now()
            )
        )
        event.value = SUBMIT_CLICKED
    }

    class Factory(context: Context) : ViewModelProvider.Factory {
        private val formatter = Item.getProperTimeAsClockPreference(context)
        private val repository = (context as TodoApplication).repository

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return HomeBottomSheetViewModel(formatter, repository) as T
        }
    }
}