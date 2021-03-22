package com.example.literacyapp.utils

import com.example.literacyapp.R
import com.example.literacyapp.model.CoursesQuiz
import com.example.literacyapp.model.WritingQuiz

object WritingQuestions{

    const val USER_WRITING_NAME: String = "user_name"
    const val TOTAL_WRITING_QUESTIONS: String = "total_question"
    const val CORRECT_WRITING_ANSWERS: String = "correct_answers"


    fun getQuestions(): ArrayList<WritingQuiz> {
        val questionsList = ArrayList<WritingQuiz>()

        val que1 = WritingQuiz(
            1,
                "I went to BLANK the leisure centre",
            "visit",
            "viisit",
            "vissit",
            "visitt",
                writingcorrectAnswer = 1
        )
        questionsList.add(que1)

        val que2 = WritingQuiz(
            2,
                "He won the game so I BLANK lunch.",
            "boauth",
            "baught",
            "bought",
            "bouht",
                writingcorrectAnswer = 3
        )
        questionsList.add(que2)

        val que3 = WritingQuiz(
            3,
                "List the missing word. _________ Zoo is in the Phoenix Park",
            "Monday",
            "Dublin",
            "Peter",
            "work",
                writingcorrectAnswer = 2
        )
        questionsList.add(que3)

        val que4 = WritingQuiz(
            4,
                "Which sentence is used when giving instructions",
            "Lots of love",
            "How was your holiday",
            "Dear Dad",
            "Please pick up after yourself",
                writingcorrectAnswer = 4
        )
        questionsList.add(que4)

        val que5 = WritingQuiz(
            5,
                "Which sentence is used when writing a letter",
            "Dear John",
            "Pay here",
            "Stop ",
            "No parking",
                writingcorrectAnswer = 1
        )
        questionsList.add(que5)

        return questionsList
    }
}
