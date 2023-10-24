package com.nocountry.c1322ftkotlin.model

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.provider.CalendarContract

object CalendarRepository {
    @SuppressLint("Range")
    fun getCalendarId(contentResolver: ContentResolver, calendarName: String): Long? {
        val projection = arrayOf(CalendarContract.Calendars._ID)
        val selection = "${CalendarContract.Calendars.CALENDAR_DISPLAY_NAME} = ?"
        val selectionArgs = arrayOf(calendarName)

        val cursor = contentResolver.query(
            CalendarContract.Calendars.CONTENT_URI,
            projection,
            selection,
            selectionArgs,
            null
        )

        cursor?.use { cursor ->
            if (cursor.moveToFirst()) {
                return cursor.getLong(cursor.getColumnIndex(CalendarContract.Calendars._ID))
            }
        }

        return null
    }
}
