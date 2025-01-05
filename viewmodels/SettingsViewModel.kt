package com.mobileapp.habittracker.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.mobileapp.habittracker.managers.DatabaseManager
import kotlinx.coroutines.launch

class SettingsViewModel(application: Application) : AndroidViewModel(application) {

    private val databaseManager : DatabaseManager = DatabaseManager(getApplication())

    fun deleteAllHabits() {
        viewModelScope.launch {
            databaseManager.habitRepository.deleteAll()
        }
    }
}