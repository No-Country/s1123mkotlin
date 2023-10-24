package com.nocountry.s1123mkotlin.screens

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reminder")
data class ReminderEntity(
    @PrimaryKey val id: String,
    val title: String,
    val date: Long,
    val time: Long,
    val repeatInterval: String
)

