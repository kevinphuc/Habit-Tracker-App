package com.mobileapp.habittracker.managers

import android.content.Context
import com.mobileapp.habittracker.database.AppDatabase
import com.mobileapp.habittracker.database.repositories.HabitRepository
import com.mobileapp.habittracker.database.repositories.TaskLogRepository

class DatabaseManager(context : Context) {
    private val db = AppDatabase.getDatabase(context)
    val habitRepository = HabitRepository(db.habitDao())
    val taskLogRepository = TaskLogRepository(db.taskLogDao())
}