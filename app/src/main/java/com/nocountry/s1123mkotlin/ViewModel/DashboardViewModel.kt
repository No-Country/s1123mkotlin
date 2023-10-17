package com.nocountry.s1123mkotlin.ViewModel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nocountry.s1123mkotlin.screens.Reminder
import com.nocountry.s1123mkotlin.screens.ReminderRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class DashboardViewModel(private val repository: ReminderRepository) : ViewModel() {
    val allReminders: Flow<List<Reminder>> = repository.allReminders

    fun insert(reminder: Reminder) {
        viewModelScope.launch {
            repository.insert(reminder)
        }
    }
}

