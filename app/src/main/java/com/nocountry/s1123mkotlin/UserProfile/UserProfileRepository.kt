package com.nocountry.s1123mkotlin.UserProfile


import androidx.lifecycle.LiveData

class UserProfileRepository(private val userProfileDao: UserProfileDao) {
    val profiles: LiveData<List<UserProfile>> = userProfileDao.getProfiles()
}
