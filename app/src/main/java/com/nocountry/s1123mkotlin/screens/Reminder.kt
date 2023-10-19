package com.nocountry.s1123mkotlin.screens

import androidx.room.Entity
import java.util.Date

enum class RepeatInterval {
    DAILY,
    WEEKLY,
    MONTHLY,
    YEARLY
}


@Entity(tableName = "reminders")
data class Reminder(
    val title: String,
    val date: Date,
    val time: Date,
    val repeatInterval: String
)

