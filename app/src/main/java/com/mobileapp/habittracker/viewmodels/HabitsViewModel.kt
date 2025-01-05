package com.mobileapp.habittracker.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.mobileapp.habittracker.managers.DatabaseManager
import com.mobileapp.habittracker.managers.IconManager
import com.mobileapp.habittracker.models.Habit

class HabitsViewModel(application: Application) : AndroidViewModel(application) {

    val iconManager = IconManager(application)
    private val databaseManager : DatabaseManager = DatabaseManager(getApplication())

    fun getHabits() : LiveData<List<Habit>> {
        return databaseManager.habitRepository.habits
    }
}