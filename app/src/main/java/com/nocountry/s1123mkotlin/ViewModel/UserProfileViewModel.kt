package com.nocountry.s1123mkotlin.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nocountry.s1123mkotlin.UserProfile.UserProfile
import com.nocountry.s1123mkotlin.screens.ReminderEntity

class UserProfileViewModel : ViewModel() {
    val userProfile = MutableLiveData<UserProfile>()
    val reminders = MutableLiveData<List<ReminderEntity>>()
}
