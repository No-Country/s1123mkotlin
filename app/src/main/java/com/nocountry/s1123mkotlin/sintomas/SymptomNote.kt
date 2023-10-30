package com.nocountry.s1123mkotlin.sintomas

import androidx.compose.ui.graphics.Color

data class SymptomNote(
    val id: Int,
    val title: String,
    val content: String,
    val backgroundColor: Color
)
