package com.nocountry.s1123mkotlin.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nocountry.s1123mkotlin.login.UserProfile
import com.nocountry.s1123mkotlin.screens.Reminder

class UserProfileViewModel : ViewModel() {
    val userProfile = MutableLiveData<UserProfile>()
    val reminders = MutableLiveData<List<Reminder>>()
}
