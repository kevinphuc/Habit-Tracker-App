package com.example.habit_tracker_app.viewmodels

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.habit_tracker_app.R
import com.example.habit_tracker_app.managers.DatabaseManager
import com.example.habit_tracker_app.managers.IconManager
import com.example.habit_tracker_app.managers.TaskManager
import com.example.habit_tracker_app.models.Habit
import com.example.habit_tracker_app.models.HabitWithTaskLogs
import com.example.habit_tracker_app.models.TaskModel
import com.example.habit_tracker_app.models.WeekDaysSelectionModel
import com.example.habit_tracker_app.utils.CalendarUtil
import com.example.habit_tracker_app.utils.HabitInfoUtil
import com.example.habit_tracker_app.utils.TaskUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskManagementViewModel(application: Application) : AndroidViewModel(application) {
    private var initialized = false

    private val databaseManager = DatabaseManager(getApplication())
    private val taskManager = TaskManager(databaseManager)
    val iconManager = IconManager(application)

    private lateinit var habitWithTaskLogs : LiveData<HabitWithTaskLogs?>

    private var selectedDateTimestamp : MutableLiveData<Long> = MutableLiveData()

    fun initialize(id : Long) {
        if (initialized) return

        selectedDateTimestamp.value = System.currentTimeMillis()
        habitWithTaskLogs = databaseManager.habitRepository.getHabitWithTaskLogsById(id)

        initialized = true
    }

    fun getHabitWithTaskLogsLiveData() : LiveData<HabitWithTaskLogs?> {
        return habitWithTaskLogs
    }

    fun createTaskLog(status : String) : Boolean {
        val habit = habitWithTaskLogs.value?: return false
        if(!canAddTaskLog()) return false

        viewModelScope.launch {
            taskManager.insertTaskLog(TaskModel(habit), status, getSelectedDateTimestamp())
        }

        return true
    }

    fun getSelectedDateTimestampLiveData() : LiveData<Long> {
        return selectedDateTimestamp
    }

    fun getSelectedDateTimestamp() : Long {
        return selectedDateTimestamp.value?: System.currentTimeMillis()
    }

    fun setSelectedDateTimestamp(timestamp: Long) {
        selectedDateTimestamp.value = timestamp
    }

    fun canAddTaskLog() : Boolean {
        val habitWithTaskLogs = habitWithTaskLogs.value?: return false
        return !TaskUtil.hasTaskLogForDate(habitWithTaskLogs, getSelectedDateTimestamp())
    }
}