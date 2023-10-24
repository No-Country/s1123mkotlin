package com.nocountry.s1123mkotlin.UserProfile

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserProfileDao {
    @Insert
    fun insert(userProfile: UserProfile)

    @Query("SELECT * FROM user_profiles WHERE profileId = :profileId")
    fun getUserProfile(profileId: Int): UserProfile

        @Query("SELECT * FROM user_profiles")
        fun getProfiles(): LiveData<List<UserProfile>>
    }

