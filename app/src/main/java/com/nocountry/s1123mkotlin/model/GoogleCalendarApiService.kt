package com.nocountry.s1123mkotlin.model

import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface GoogleCalendarApiService {
    @GET("events")
    suspend fun getEvents(@Query("apiKey") apiKey: String): List<CalendarEvent>

    @POST("events")
    suspend fun createEvent(@Query("apiKey") apiKey: String, @Body event: CalendarEvent)

    @PUT("events/{eventId}")
    suspend fun updateEvent(
        @Query("apiKey") apiKey: String,
        @Path("eventId") eventId: String,
        @Body event: CalendarEvent
    )

    @DELETE("events/{eventId}")
    suspend fun deleteEvent(
        @Query("apiKey") apiKey: String,
        @Path("eventId") eventId: String
    )
}