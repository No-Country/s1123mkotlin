package com.nocuntry.c1322ftkotlin.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nocountry.s1123mkotlin.model.CalendarEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GoogleCalendarViewModel(private val repository: GoogleCalendarRepository) : ViewModel() {

    private val _calendarEvents = MutableLiveData<List<CalendarEvent>>()
    val calendarEvents: LiveData<List<CalendarEvent>>
        get() = _calendarEvents

    fun loadCalendarEvents(apiKey: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val events = repository.getCalendarEvents(apiKey)
            _calendarEvents.postValue(events)
        }
    }

    fun createCalendarEvent(apiKey: String, event: CalendarEvent) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.createCalendarEvent(apiKey, event)
            // Actualiza la lista de eventos después de crear uno nuevo
            loadCalendarEvents(apiKey)
        }
    }

    // Otras funciones para actualizar y eliminar eventos
}
