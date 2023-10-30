package com.nocountry.s1123mkotlin.sintomas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SymptomNotesViewModel : ViewModel() {

    private val symptomNotes = mutableListOf<SymptomNote>()
    private val symptomNotesLiveData = MutableLiveData<List<SymptomNote>>()

    fun getSymptomNotesLiveData(): LiveData<List<SymptomNote> > {
        symptomNotesLiveData.value = symptomNotes.toList()
        return symptomNotesLiveData
    }

    fun addSymptomNote(note: SymptomNote) {
        symptomNotes.add(note)
        symptomNotesLiveData.value = symptomNotes.toList()
    }

    fun getSymptomNoteById(noteId: Int): SymptomNote? {
        return symptomNotes.find { it.id == noteId }
    }

    // Actualizar una nota de síntoma existente
    fun updateSymptomNote(updatedNote: SymptomNote) {
        val index = symptomNotes.indexOfFirst { it.id == updatedNote.id }
        if (index != -1) {
            symptomNotes[index] = updatedNote
            symptomNotesLiveData.value = symptomNotes.toList()
        }
    }

    // Eliminar una nota de síntoma
    fun deleteSymptomNote(note: SymptomNote) {
        symptomNotes.remove(note)
        symptomNotesLiveData.value = symptomNotes.toList()
    }
}
