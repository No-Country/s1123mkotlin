package com.nocountry.s1123mkotlin.ViewModel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nocountry.s1123mkotlin.screens.ReminderEntity
import com.nocountry.s1123mkotlin.screens.ReminderRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class DashboardViewModel(private val repository: ReminderRepository) : ViewModel() {
    val allReminders: Flow<List<ReminderEntity>> = repository.allReminders

    fun insert(reminder: ReminderEntity) {
        viewModelScope.launch {
            repository.insert(reminder)
        }
    }
}

