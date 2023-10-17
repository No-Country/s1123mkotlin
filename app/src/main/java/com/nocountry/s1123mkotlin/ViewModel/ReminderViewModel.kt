package com.nocountry.s1123mkotlin.ViewModel

import androidx.lifecycle.ViewModel
import com.nocountry.s1123mkotlin.screens.Reminder
import com.nocountry.s1123mkotlin.screens.ReminderRepository

class ReminderViewModel(
    private val repository: ReminderRepository)
{
    val reminderViewModel = ReminderViewModel(ReminderRepository())

    private val reminders = mutableListOf<Reminder>()

    fun addReminder(reminder: Reminder) {
        reminders.add(reminder)
    }

    fun getReminders(): List<Reminder> {
        return reminders
    }

    fun setAlarm(reminder: Reminder) {
        // TODO: Set an alarm for the reminder
    }

}
