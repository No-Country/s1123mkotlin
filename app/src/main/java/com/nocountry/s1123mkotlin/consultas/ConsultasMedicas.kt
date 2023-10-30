package com.nocountry.s1123mkotlin.consultas

import android.icu.util.Calendar

data class ConsultaMedica(
    val id: String,
    val fecha: String,
    val nombreMedico: String,
    val ubicacion: String,
)