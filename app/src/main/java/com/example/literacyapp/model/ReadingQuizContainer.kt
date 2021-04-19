package com.example.literacyapp.model

import android.provider.BaseColumns

object ReadingQuizContainer {

    class CategoriesTable : BaseColumns {
        companion object {
            val _ID = "categoryID"
            val TABLE_NAME = "quiz_category"
            val COLUMN_NAME = "name"
            //val CATEGORY_ID = "categoryID"
        }

    }
        class QuizTable : BaseColumns {
            companion object {

                val _ID = "quiz_id"
                val QUESTION_TABLE_NAME = "quiz_question"
                val QUESTION_COLUMN = "question"
                val OPTION1_COLUMN = "option1"
                val OPTION2_COLUMN = "option2"
                val OPTION3_COLUMN = "option3"
                val ANS_COLUMN = "ans"
                val COLUMN_CATEGORY_ID = "categoryID"
            }


        }


    }
