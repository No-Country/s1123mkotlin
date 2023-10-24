package com.nocountry.s1123mkotlin.screens

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ReminderDao {
    @Insert
    suspend fun insert(reminder: ReminderEntity)

    @Query("SELECT * FROM reminder")
    fun getAllReminders(): Flow<List<ReminderEntity>>
}
