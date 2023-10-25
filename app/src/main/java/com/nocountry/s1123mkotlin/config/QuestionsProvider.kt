package com.nocountry.s1123mkotlin.config

import com.nocountry.s1123mkotlin.model.QuestionResponse


class QuestionsProvider {
    companion object {
        fun getQuestions(): List<QuestionResponse> {
            return listOf(
                QuestionResponse(
                    "¿Cuáles son los síntomas comunes de la gripe?"
                ),
                QuestionResponse(
                    "Explícame la importancia de la vacunación infantil."
                ),
                QuestionResponse(
                    "Háblame sobre los sintomas de la varicela."
                ),
                QuestionResponse(
                    "¿Qué es la presión arterial y cómo se mide?"
                ),
                QuestionResponse(
                    "Explícame los primeros auxilios en caso de quemaduras."
                )
            )
        }
    }
}
