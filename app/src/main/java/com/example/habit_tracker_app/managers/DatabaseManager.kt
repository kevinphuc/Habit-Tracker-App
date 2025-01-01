package com.example.habit_tracker_app.managers

import android.content.Context
import com.example.habit_tracker_app.database.AppDatabase
import com.example.habit_tracker_app.database.repositories.HabitRepository
import com.example.habit_tracker_app.database.repositories.TaskLogRepository

class DatabaseManager(context : Context) {
    private val db = AppDatabase.getDatabase(context)
    val habitRepository = HabitRepository(db.habitDao())
    val taskLogRepository = TaskLogRepository(db.taskLogDao())
}