package com.nocuntry.c1322ftkotlin.model

import com.nocountry.s1123mkotlin.model.CalendarEvent

class GoogleCalendarRepository(private val apiClient: GoogleCalendarApiClient) {

    suspend fun getCalendarEvents(apiKey: String): List<CalendarEvent> {
        return apiClient.getCalendarEvents(apiKey)
    }

    suspend fun createCalendarEvent(apiKey: String, event: CalendarEvent) {
        apiClient.createCalendarEvent(apiKey, event)
    }

}
