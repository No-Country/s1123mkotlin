package com.nocountry.s1123mkotlin.ViewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.nocountry.s1123mkotlin.di.AppDatabase
import com.nocountry.s1123mkotlin.screens.ReminderEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RemindersScreenViewModel(private val context: Context) : ViewModel() {
    private val database: AppDatabase = AppDatabase.getDatabase(context)
    val allReminders: LiveData<List<ReminderEntity>> =
        database.reminderDao().getAllReminders().asLiveData()

    fun deleteReminder(reminder: ReminderEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            // Utiliza el DAO para eliminar el recordatorio de la base de datos
            database.reminderDao().delete(reminder)
        }
    }
}
