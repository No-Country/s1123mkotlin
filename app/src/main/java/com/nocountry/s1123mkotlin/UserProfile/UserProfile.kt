package com.nocountry.s1123mkotlin.UserProfile


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_profiles")
data class UserProfile(
    @PrimaryKey
    val profileId: Int = 0,
    val name: String,
    val imageResId: Int
)
