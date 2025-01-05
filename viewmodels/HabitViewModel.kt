package com.mobileapp.habittracker.viewmodels

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mobileapp.habittracker.R
import com.mobileapp.habittracker.managers.DatabaseManager
import com.mobileapp.habittracker.managers.IconManager
import com.mobileapp.habittracker.models.Habit
import com.mobileapp.habittracker.models.HabitWithTaskLogs
import com.mobileapp.habittracker.models.WeekDaysSelectionModel
import com.mobileapp.habittracker.utils.CalendarUtil
import com.mobileapp.habittracker.utils.HabitInfoUtil
import kotlinx.coroutines.launch

class HabitViewModel(application: Application) : AndroidViewModel(application) {
    private var initialized = false

    private val databaseManager = DatabaseManager(getApplication())
    val iconManager = IconManager(application)

    private lateinit var habitWithTaskLogs : LiveData<HabitWithTaskLogs?>

    //Set true to exit the fragment
    private val shouldExitView : MutableLiveData<Boolean> = MutableLiveData<Boolean>()

    fun initialize(id : Long) {
        if (initialized) return

        habitWithTaskLogs = databaseManager.habitRepository.getHabitWithTaskLogsById(id)

        initialized = true
    }

    fun getRecurrenceText(context: Context, habit: Habit) : String {
        val weekDaysSelectionModel = WeekDaysSelectionModel()
        CalendarUtil.parseRRULEtoWeekDaysSelectionModel(habit.taskRecurrence, weekDaysSelectionModel)

        return HabitInfoUtil.getRecurrenceHeader(context, weekDaysSelectionModel)
    }

    fun deleteHabit(context: Context) {
        val habitWithTaskLogsToDelete = habitWithTaskLogs.value

        if(habitWithTaskLogsToDelete != null) {
            val habitToDelete = habitWithTaskLogsToDelete.habit
            val habitName = habitToDelete.name

            viewModelScope.launch {
                val rows = databaseManager.habitRepository.deleteHabit(habitToDelete)

                if(rows > 0) {
                    shouldExitView.value = true
                    Toast.makeText(context, context.getString(R.string.deleted, habitName), Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(context, context.getString(R.string.error_delete_habit), Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    fun setHabitEnabled(enabled : Boolean) {
        val habitWithTaskLogs = habitWithTaskLogs.value

        if(habitWithTaskLogs != null) {
            val habit = habitWithTaskLogs.habit
            habit.disabled = !enabled

            viewModelScope.launch {
                databaseManager.habitRepository.updateHabit(habit)
            }
        }
    }

    fun getHabitWithTaskLogsLiveData() : LiveData<HabitWithTaskLogs?> {
        return habitWithTaskLogs
    }

    fun getShouldExitView() : LiveData<Boolean> {
        return shouldExitView
    }
}