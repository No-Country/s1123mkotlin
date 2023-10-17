package com.nocountry.s1123mkotlin.screens

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalTime

enum class RepeatInterval {
    DAILY,
    WEEKLY,
    MONTHLY,
    YEARLY
}


@Entity(tableName = "reminders")
data class Reminder(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val date: LocalDate,
    val time: LocalTime,
    val repeatInterval: RepeatInterval
)

