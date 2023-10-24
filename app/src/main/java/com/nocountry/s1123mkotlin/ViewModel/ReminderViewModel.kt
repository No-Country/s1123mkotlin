package com.nocountry.s1123mkotlin.ViewModel

import androidx.lifecycle.ViewModel
import com.nocountry.s1123mkotlin.screens.ReminderEntity
import com.nocountry.s1123mkotlin.screens.ReminderRepository

class ReminderViewModel(private val repository: ReminderRepository) : ViewModel() {
    private val reminders = mutableListOf<ReminderEntity>()

    fun addReminder(reminder: ReminderEntity) {
        reminders.add(reminder)
    }

    fun getReminders(): List<ReminderEntity> {
        return reminders
    }

    fun setAlarm(reminder: ReminderEntity) {
        // TODO: Set an alarm for the reminder
    }
}
