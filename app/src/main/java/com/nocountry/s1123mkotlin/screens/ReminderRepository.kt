package com.nocountry.s1123mkotlin.screens

import android.content.ContentValues
import android.provider.CalendarContract
import androidx.core.content.ContentResolverCompat
import com.nocountry.c1322ftkotlin.model.CalendarRepository
import com.nocountry.s1123mkotlin.screens.ReminderDao
import com.nocountry.s1123mkotlin.screens.ReminderEntity
import kotlinx.coroutines.Dispatchers
import android.content.Context
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ReminderRepository @Inject constructor(
    private val reminderDao: ReminderDao,
    private val context: Context
) {
    val allReminders = reminderDao.getAllReminders()

    suspend fun insert(reminder: ReminderEntity) {
        reminderDao.insert(reminder)
        createCalendarEvent(reminder)
    }

    suspend fun update(reminder: ReminderEntity) {
        reminderDao.update(reminder)
        updateCalendarEvent(reminder)
    }

    suspend fun delete(reminder: ReminderEntity) {
        reminderDao.delete(reminder)
        deleteCalendarEvent(reminder)
    }

    private suspend fun createCalendarEvent(reminder: ReminderEntity) {
        val calendarId = CalendarRepository.getCalendarId(context.contentResolver, "Calendario de Recordatorios")
        if (calendarId != null) {
            val event = ContentValues().apply {
                put(CalendarContract.Events.CALENDAR_ID, calendarId)
                put(CalendarContract.Events.TITLE, reminder.title)
                put(CalendarContract.Events.DESCRIPTION, "Descripción del Recordatorio")
                put(CalendarContract.Events.EVENT_TIMEZONE, "America/New_York")
                put(CalendarContract.Events.DTSTART, reminder.date)
                put(CalendarContract.Events.DTEND, reminder.date + 3600000) // Duración de 1 hora
            }

            withContext(Dispatchers.IO) {
                context.contentResolver.insert(CalendarContract.Events.CONTENT_URI, event)
            }
        }
    }

    private suspend fun updateCalendarEvent(reminder: ReminderEntity) {
        // Implementa la actualización de eventos de calendario si es necesario
    }

    private suspend fun deleteCalendarEvent(reminder: ReminderEntity) {
        // Implementa la eliminación de eventos de calendario si es necesario
    }
}
