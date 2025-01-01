package com.example.habit_tracker_app.models

import androidx.room.Embedded
import androidx.room.Relation
import com.example.habit_tracker_app.utils.ScoreUtil

data class HabitWithTaskLogs (
    @Embedded
    val habit: Habit,
    @Relation(
        parentColumn = "id",
        entityColumn = "habitId"
    )
    val taskLogs: List<TaskLog>
) {
    init {
        val shouldResetHabitScore = ScoreUtil.shouldResetHabitScore(this)
        if(shouldResetHabitScore) habit.score = 0
    }
}