package com.nocountry.s1123mkotlin.login


import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserProfileRepository(private val userProfileDao: UserProfileDao) {
    val profiles: LiveData<List<UserProfile>> = userProfileDao.getProfiles()
}
