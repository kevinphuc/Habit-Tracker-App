package com.example.habit_tracker_app.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.habit_tracker_app.managers.DatabaseManager
import com.example.habit_tracker_app.managers.IconManager
import com.example.habit_tracker_app.managers.TaskManager
import com.example.habit_tracker_app.models.HabitWithTaskLogs
import com.example.habit_tracker_app.models.TaskModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
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