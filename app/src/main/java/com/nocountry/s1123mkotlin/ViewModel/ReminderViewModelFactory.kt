package com.nocountry.s1123mkotlin.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nocountry.s1123mkotlin.screens.ReminderRepository

class ReminderViewModelFactory : ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass == ReminderViewModel::class.java) {
            return ReminderViewModel(ReminderRepository()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
