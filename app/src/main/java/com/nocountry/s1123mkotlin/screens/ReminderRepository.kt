package com.nocountry.s1123mkotlin.screens

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReminderRepository @Inject constructor(private val reminderDao: ReminderDao) {
    val allReminders: Flow<List<ReminderEntity>> = reminderDao.getAllReminders()

    suspend fun insert(reminder: ReminderEntity) {
        reminderDao.insert(reminder)
    }
}
