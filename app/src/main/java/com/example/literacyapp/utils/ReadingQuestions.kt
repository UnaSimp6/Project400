package com.example.literacyapp.utils

import com.example.literacyapp.R
import com.example.literacyapp.model.CoursesQuiz

object ReadingQuestions{

    const val USER_READING_NAME: String = "user_name"
    const val TOTAL_READING_QUESTIONS: String = "total_question"
    const val CORRECT_READING_ANSWERS: String = "correct_answers"


    fun getQuestions(): ArrayList<CoursesQuiz> {
        val questionsList = ArrayList<CoursesQuiz>()

        val que1 = CoursesQuiz(
            1,

                "My female spouse is my",
            "grandmother",
            "mother-in-law",
            "wife",
            "daughter",
            correctAnswer = 3
        )
        questionsList.add(que1)

        val que2 = CoursesQuiz(
            2,
                "What is the opposite of Empty?",
            "Moon",
            "Angry",
            "Full",
            "Sad",
            correctAnswer = 3
        )
        questionsList.add(que2)

        val que3 = CoursesQuiz(
            3,
                "Below are four versions of a sentence from a student’s assignment. Which version has acceptable punctuation?",
            "‘Our community, is not static,’ she said. ‘It is constantly changing.’",
            "‘Our community is not static’ she said ‘it is constantly changing.’",
            "‘Our community is not static,’ she said. ‘It is constantly changing.’",
            "‘Our community is not static, she said, it is constantly changing.’",
            correctAnswer = 3
        )
        questionsList.add(que3)

        val que4 = CoursesQuiz(
            4,
                "Which sentence has the correct grammar?",
            "Your looking good",
            "your, looking good",
            "Youre looking food",
            "You're looking good",
            correctAnswer = 4
        )
        questionsList.add(que4)

        val que5 = CoursesQuiz(
            5,
                "Jack breaks into rich people's homes and steals money and jewellery. He's an expert at",
            "burglary",
            "treachery",
            "bigamy ",
            "forgery",
            correctAnswer = 1
        )
        questionsList.add(que5)

        return questionsList
    }
}
