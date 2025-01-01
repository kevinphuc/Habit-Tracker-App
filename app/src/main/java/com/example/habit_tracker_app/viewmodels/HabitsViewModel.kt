package com.example.habit_tracker_app.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.habit_tracker_app.managers.DatabaseManager
import com.example.habit_tracker_app.managers.IconManager
import com.example.habit_tracker_app.models.Habit

class HabitsViewModel(application: Application) : AndroidViewModel(application) {

    val iconManager = IconManager(application)
    private val databaseManager : DatabaseManager = DatabaseManager(getApplication())

    fun getHabits() : LiveData<List<Habit>> {
        return databaseManager.habitRepository.habits
    }
}