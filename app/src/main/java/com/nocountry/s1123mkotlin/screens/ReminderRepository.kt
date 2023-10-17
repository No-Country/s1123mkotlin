package com.nocountry.s1123mkotlin.screens


import com.nocountry.s1123mkotlin.screens.Reminder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class ReminderRepository {
    private val reminders = mutableListOf<Reminder>()

    private val _allReminders: MutableStateFlow<List<Reminder>> = MutableStateFlow(emptyList())

    val allReminders: Flow<List<Reminder>> = _allReminders

    fun getAll(): List<Reminder> {
        return reminders
    }

    fun save(reminder: Reminder) {
        reminders.add(reminder)
        _allReminders.value = reminders.toList() // Actualiza la secuencia con la nueva lista
    }

    fun delete(reminder: Reminder) {
        reminders.remove(reminder)
        _allReminders.value = reminders.toList() // Actualiza la secuencia con la nueva lista
    }
    suspend fun insert(reminder: Reminder) {
        // Implementa la lógica de inserción aquí
    }
}
