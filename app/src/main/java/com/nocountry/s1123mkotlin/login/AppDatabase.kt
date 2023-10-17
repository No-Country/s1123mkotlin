package com.nocountry.s1123mkotlin.login

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nocountry.s1123mkotlin.login.UserProfile
import com.nocountry.s1123mkotlin.login.UserProfileDao

@Database(entities = [UserProfile::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userProfileDao(): UserProfileDao
}
