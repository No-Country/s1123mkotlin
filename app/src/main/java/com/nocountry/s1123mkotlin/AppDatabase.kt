package com.nocountry.s1123mkotlin

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nocountry.s1123mkotlin.UserProfile.UserProfile
import com.nocountry.s1123mkotlin.UserProfile.UserProfileDao
import com.nocountry.s1123mkotlin.screens.ReminderDao
import com.nocountry.s1123mkotlin.screens.ReminderEntity

@Database(entities = [UserProfile::class, ReminderEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userProfileDao(): UserProfileDao
    abstract fun reminderDao(): ReminderDao
}
