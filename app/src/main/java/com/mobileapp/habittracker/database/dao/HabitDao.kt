package com.mobileapp.habittracker.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mobileapp.habittracker.models.Habit
import com.mobileapp.habittracker.models.HabitWithTaskLogs

@Dao
interface HabitDao {
    @Query("SELECT * FROM habit")
    fun getAll(): LiveData<List<Habit>>

    @Transaction
    @Query("SELECT * FROM habit")
    fun getHabitsWithTaskLogs(): LiveData<List<HabitWithTaskLogs>>

    @Query("SELECT * FROM habit WHERE id IN (:id)")
    suspend fun getById(id : Long) : Habit?

    @Query("SELECT * FROM habit WHERE id IN (:id)")
    fun getHabitWithTaskLogsById(id : Long) : LiveData<HabitWithTaskLogs?>

    @Insert
    suspend fun create(habit: Habit) : Long

    @Update
    suspend fun update(habit: Habit) : Int

    @Delete
    suspend fun delete(habit: Habit) : Int

    @Query("DELETE FROM habit")
    suspend fun deleteAll() : Int
}