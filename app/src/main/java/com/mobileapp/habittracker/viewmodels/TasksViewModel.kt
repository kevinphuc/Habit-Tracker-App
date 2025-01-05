package com.mobileapp.habittracker.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.mobileapp.habittracker.managers.DatabaseManager
import com.mobileapp.habittracker.managers.IconManager
import com.mobileapp.habittracker.managers.TaskManager
import com.mobileapp.habittracker.models.HabitWithTaskLogs
import com.mobileapp.habittracker.models.TaskModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TasksViewModel(application: Application) : AndroidViewModel(application) {
    private var hasHabits = false
    private val databaseManager = DatabaseManager(application)
    private val taskManager = TaskManager(databaseManager)
    val iconManager = IconManager(application)

    fun createTaskLog(taskModel: TaskModel, status : String) {
        viewModelScope.launch(Dispatchers.IO) {
            taskManager.insertTaskLog(taskModel, status, System.currentTimeMillis())
        }
    }

    fun generateDailyTasks(habits : List<HabitWithTaskLogs>) {
        hasHabits = habits.isNotEmpty()

        viewModelScope.launch(Dispatchers.Main) {
            taskManager.generateDailyTasks(habits)
        }
    }

    fun hasHabits() : Boolean {
        return hasHabits
    }

    fun getHabitsWithTaskLogs() : LiveData<List<HabitWithTaskLogs>> {
        return databaseManager.habitRepository.habitsWithTaskLogs
    }

    fun getTasks() : LiveData<ArrayList<TaskModel>> {
        return taskManager.tasks
    }
}