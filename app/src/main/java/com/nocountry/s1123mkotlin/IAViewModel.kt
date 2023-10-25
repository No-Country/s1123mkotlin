package com.nocuntry.medichild.viewmodel

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aallam.openai.api.chat.ChatCompletionRequest
import com.aallam.openai.api.chat.ChatMessage
import com.aallam.openai.api.chat.ChatRole
import com.aallam.openai.client.OpenAI
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.launch
import javax.inject.Inject


class IAViewModel @Inject constructor() : ViewModel() {
    val chatList = mutableListOf<Pair<String, Color>>()
    var welcomeQuestion = true

    private val openaiApiKey = "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" // Reemplaza con tu clave

    private val openAI = OpenAI(token = openaiApiKey)


    init {
        // Saludo inicial
        addMessage("¡Hola! Soy tu asistente de IA para temas médicos básicos. ¿En qué puedo ayudarte hoy?", Color.DarkGray)
    }

    fun sendMessage(message: String) {
        if (message.isNotBlank()) {
            addMessage("Tú: $message", Color.Gray)
            processUserMessage(message)
        }
    }

    private fun processUserMessage(message: String) {
        // Procesa el mensaje del usuario y obtén una respuesta de la IA
        val aiResponse = getAIResponse(message)
        addMessage("IA: $aiResponse", Color.Green)
    }

    private fun getAIResponse(userMessage: String): String {
        // Aquí puedes realizar una solicitud a la API de IA para obtener una respuesta basada en el mensaje del usuario.
        // Debes configurar adecuadamente tu cliente de IA para esta parte.

        // Por ahora, simplemente devolvemos una respuesta aleatoria.
        val randomResponses = listOf("Entendido", "No entiendo la pregunta", "Eso es interesante", "Por favor, sé más específico")
        return randomResponses.random()
    }

    private fun addMessage(message: String, color: Color) {
        chatList.add(message to color)
        welcomeQuestion = false
    }
}
