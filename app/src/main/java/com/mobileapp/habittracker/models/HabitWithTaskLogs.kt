package com.mobileapp.habittracker.models

import androidx.room.Embedded
import androidx.room.Relation
import com.mobileapp.habittracker.utils.ScoreUtil

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