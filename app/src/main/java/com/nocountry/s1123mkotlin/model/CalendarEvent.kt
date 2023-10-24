package com.nocountry.s1123mkotlin.model

data class CalendarEvent(
    val id: String,
    val title: String,
    val description: String,
    val startTime: Long,
    val endTime: Long
)