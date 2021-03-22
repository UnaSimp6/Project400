package com.example.literacyapp.utils

import com.example.literacyapp.R
import com.example.literacyapp.model.CommunicationsQuiz

object CommunicationsQuestions{

    const val USER_NAME: String = "user_name"
    const val TOTAL_COMMUNICATIONS_QUESTIONS: String = "total_question"
    const val CORRECT_COMMUNICATIONS_ANSWERS: String = "correct_answers"


    fun getQuestions(): ArrayList<CommunicationsQuiz> {
        val questionsList = ArrayList<CommunicationsQuiz>()

        val que1 = CommunicationsQuiz(
            1,
                R.drawable.ic_comm,
                "What kind of mayonnaise is used in this recipe",
            "Low-fat",
            "Full-fat",
            "Half-fat",
            "Fat-free",
            correctAnswer = 1
        )
        questionsList.add(que1)

        val que2 = CommunicationsQuiz(
            2,
                R.drawable.ic_comm,
                "What kind of peas are optional?",
            "Garden peas",
            "Mushy peas",
            "Processed peas",
            "Chickpeas",
            correctAnswer = 4
        )
        questionsList.add(que2)

        val que3 = CommunicationsQuiz(
            3,
                R.drawable.ic_comm,
                "How much lemon juice should you add?",
            "1 ½ tablespoons",
            "1 tablespoon",
            "½ tablespoons",
            "1 ½ teasspoons",
            correctAnswer = 1
        )
        questionsList.add(que3)

        val que4 = CommunicationsQuiz(
            4,
                R.drawable.ic_comm,
                "How much white pepper should be added?",
            "½ cup",
            "½ tablespoon",
            "½ teaspoon",
            "1 ½ teaspoon",
            correctAnswer = 3
        )
        questionsList.add(que4)

        val que5 = CommunicationsQuiz(
            5,
                R.drawable.ic_comm,
                "How should you prepare the celery",
            "finely chopped",
            "diced",
            "grated",
            "whole",
            correctAnswer = 2
        )
        questionsList.add(que5)

        return questionsList
    }
}
