package com.example.literacyapp.adapters

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import com.example.literacyapp.data.ReadingQuestion
import com.example.literacyapp.model.ReadingQuizContainer.QuizTable.Companion.ANS_COLUMN
import com.example.literacyapp.model.ReadingQuizContainer.QuizTable.Companion.OPTION1_COLUMN
import com.example.literacyapp.model.ReadingQuizContainer.QuizTable.Companion.OPTION2_COLUMN
import com.example.literacyapp.model.ReadingQuizContainer.QuizTable.Companion.OPTION3_COLUMN
import com.example.literacyapp.model.ReadingQuizContainer.QuizTable.Companion.QUESTION_COLUMN
import com.example.literacyapp.model.ReadingQuizContainer.QuizTable.Companion.QUESTION_TABLE_NAME
import java.util.ArrayList


class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    private var db: SQLiteDatabase? = null

    val questionSet: List<ReadingQuestion>
        get() {

            val questionSetsList = ArrayList<ReadingQuestion>()

            db = readableDatabase

            val c = db!!.rawQuery("SELECT * FROM $QUESTION_TABLE_NAME", null)

            if (c.moveToFirst()) {
                do {
                    val question = ReadingQuestion()
                    question.setmQuestion(c.getString(c.getColumnIndex(QUESTION_COLUMN)))
                    question.setmOption1(c.getString(c.getColumnIndex(OPTION1_COLUMN)))
                    question.setmOption2(c.getString(c.getColumnIndex(OPTION2_COLUMN)))
                    question.setmOption3(c.getString(c.getColumnIndex(OPTION3_COLUMN)))
                    question.setmRightAns(c.getInt(c.getColumnIndex(ANS_COLUMN)))
                    questionSetsList.add(question)
                } while (c.moveToNext())

            }
            c.close()
            return questionSetsList

        }


    override fun onCreate(db: SQLiteDatabase) {
        this.db = db

        val QB_TABLE = "CREATE TABLE " +
                QUESTION_TABLE_NAME + " ( " +
                BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QUESTION_COLUMN + " TEXT, " +
                OPTION1_COLUMN + " TEXT, " +
                OPTION2_COLUMN + " TEXT, " +
                OPTION3_COLUMN + " TEXT, " +
                ANS_COLUMN + " INTEGER " +
                " )"


        db.execSQL(QB_TABLE)

        GenerateQuestionFunction()

    }
    private fun GenerateQuestionFunction() {
        val q1 = ReadingQuestion("In which word would you find a piece of jewellery", "draft", "linking", "ordering", 3)
        addQuestion(q1)
        val q2 = ReadingQuestion("In which word would you find something you sail on", "facts", "draft", "sentence", 2)
        addQuestion(q2)
        val q3 = ReadingQuestion("In which word would you find a thing that movie stars do", "facts", "sentence", "ordering", 1)
        addQuestion(q3)
        val q4 = ReadingQuestion("In which word would you find a number", "sentence", "linking", "draft", 1)
        addQuestion(q4)
        val q5 = ReadingQuestion("In which word would you find royalty", "ordering", "linking", "sentence", 2)
        addQuestion(q5)
    }

    private fun addQuestion(qb: ReadingQuestion) {
        val contentValues = ContentValues()
        contentValues.put(QUESTION_COLUMN, qb.getmQuestion())
        contentValues.put(OPTION1_COLUMN, qb.getmOption1())
        contentValues.put(OPTION2_COLUMN, qb.getmOption2())
        contentValues.put(OPTION3_COLUMN, qb.getmOption3())
        contentValues.put(ANS_COLUMN, qb.getmRightAns())
        db!!.insert(QUESTION_TABLE_NAME, null, contentValues)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }
    companion object {

        private val DATABASE_NAME = "ReadingQuiz.db"
        private val DATABASE_VERSION = 1
    }

}
