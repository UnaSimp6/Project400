package com.example.literacyapp.utils

import com.example.literacyapp.R
import com.example.literacyapp.model.CoursesQuiz
import com.example.literacyapp.model.ListeningQuiz

object ListeningQuestions{

    const val USER_NAME: String = "user_name"
    const val TOTAL_LISTENING_QUESTIONS: String = "total_question"
    const val CORRECT_LISTENING_ANSWERS: String = "correct_answers"


    fun getQuestions(): ArrayList<ListeningQuiz> {
        val questionsList = ArrayList<ListeningQuiz>()

        val que1 = ListeningQuiz(
            1,

                "The next train from platform _________ serves Cork",
            "50",
            "5",
            "15",
            "4",
            correctAnswer = 2
        )
        questionsList.add(que1)

        val que2 = ListeningQuiz(
            2,
                "The train stops at _________ and Cork",
            "Limerick Junction",
            "Mallow",
            "Hueston",
            "Charlestown",
            correctAnswer = 1
        )
        questionsList.add(que2)

        val que3 = ListeningQuiz(
            3,
                "_________ for Limerick should change at Limerick Junction.",
            "Staff",
            "People",
            "Customers",
            "Passengers",
            correctAnswer = 4
        )
        questionsList.add(que3)

        val que4 = ListeningQuiz(
            4,
                "The train is scheduled to _________ in Kent Station Cork.",
            "leave",
            "be",
            "arrive",
            "depart",
            correctAnswer = 3
        )
        questionsList.add(que4)

        val que5 = ListeningQuiz(
            5,
                "What time is the train scheduled to arrive?",
            "Twenty to Seven",
            "Twenty to Six",
            "Seven fourty ",
            "Twenty past Seven",
            correctAnswer = 1
        )
        questionsList.add(que5)

        return questionsList
    }
}
