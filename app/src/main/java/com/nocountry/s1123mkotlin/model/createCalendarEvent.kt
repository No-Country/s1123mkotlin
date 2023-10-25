package com.nocountry.s1123mkotlin.model

import android.content.ContentValues
import android.content.Context
import android.provider.CalendarContract
import android.content.Intent
import android.app.Activity
import android.content.ContentResolver
import android.icu.util.Calendar
import com.nocountry.c1322ftkotlin.model.CalendarRepository.getCalendarId
import com.nocountry.s1123mkotlin.screens.ReminderEntity


fun createCalendarEvent(context: Context, title: String, dateInMillis: Long): Long {
    val cr: ContentResolver = context.contentResolver
    val event = ContentValues()
    event.put(CalendarContract.Events.CALENDAR_ID, 1) // ID de tu calendario (debes buscar el ID correcto)
    event.put(CalendarContract.Events.TITLE, title)
    event.put(CalendarContract.Events.DTSTART, dateInMillis)
    event.put(CalendarContract.Events.DTEND, dateInMillis + 3600000) // Duración de 1 hora
    event.put(CalendarContract.Events.ALL_DAY, 0)

    val uri = cr.insert(CalendarContract.Events.CONTENT_URI, event)
    return uri?.lastPathSegment?.toLong() ?: -1L
}
