package com.nocuntry.c1322ftkotlin.model

import com.nocountry.s1123mkotlin.model.CalendarEvent
import com.nocountry.s1123mkotlin.model.GoogleCalendarApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class GoogleCalendarApiClient(private val apiKey: String) {

    private val apiService: GoogleCalendarApiService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.googlecalendar.com/") // Reemplaza con la URL real de la API
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(GoogleCalendarApiService::class.java)
    }

    suspend fun getCalendarEvents(apiKey: String): List<CalendarEvent> {
        return apiService.getEvents(this.apiKey)
    }

    suspend fun createCalendarEvent(apiKey: String, event: CalendarEvent) {
        apiService.createEvent(this.apiKey, event)
    }

    suspend fun updateCalendarEvent(eventId: String, updatedEvent: CalendarEvent) {
        apiService.updateEvent(apiKey, eventId, updatedEvent)
    }

    suspend fun deleteCalendarEvent(eventId: String) {
        apiService.deleteEvent(apiKey, eventId)
    }
}
