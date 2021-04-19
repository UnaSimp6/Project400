package com.example.literacyapp.adapters

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.literacyapp.data.Category
import com.example.literacyapp.data.ReadingQuestion
import com.example.literacyapp.model.ReadingQuizContainer
import com.example.literacyapp.model.ReadingQuizContainer.CategoriesTable
import java.util.*


class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    private var db: SQLiteDatabase? = null

    //val questionSet: List<ReadingQuestion>
  /*      get() {

            val questionSetsList = ArrayList<ReadingQuestion>()

            db = readableDatabase

            val c = db!!.rawQuery("SELECT * FROM QuizTable.QUESTION_TABLE_NAME", null)

            if (c.moveToFirst()) {
                do {
                    val question = ReadingQuestion()
                    question.setId(c.getInt(c.getColumnIndex(ReadingQuizContainer.QuizTable._ID)))
                    question.setmQuestion(c.getString(c.getColumnIndex(ReadingQuizContainer.QuizTable.QUESTION_COLUMN)))
                    question.setmOption1(c.getString(c.getColumnIndex(ReadingQuizContainer.QuizTable.OPTION1_COLUMN)))
                    question.setmOption2(c.getString(c.getColumnIndex(ReadingQuizContainer.QuizTable.OPTION2_COLUMN)))
                    question.setmOption3(c.getString(c.getColumnIndex(ReadingQuizContainer.QuizTable.OPTION3_COLUMN)))
                    question.setmRightAns(c.getInt(c.getColumnIndex(ReadingQuizContainer.QuizTable.ANS_COLUMN)))
                    question.setCategoryID(c.getInt(c.getColumnIndex(ReadingQuizContainer.QuizTable.COLUMN_CATEGORY_ID)));
                    questionSetsList.add(question)
                } while (c.moveToNext())

            }
            c.close()
            return questionSetsList

        }*/


    override fun onCreate(db: SQLiteDatabase) {
        this.db = db

        var CAT_TABLE = "CREATE TABLE " +
                ReadingQuizContainer.CategoriesTable.TABLE_NAME + "( " +
                ReadingQuizContainer.CategoriesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ReadingQuizContainer.CategoriesTable.COLUMN_NAME + " TEXT " +
                ")"

        val QB_TABLE = "CREATE TABLE " +
                ReadingQuizContainer.QuizTable.QUESTION_TABLE_NAME + " ( " +
                ReadingQuizContainer.QuizTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ReadingQuizContainer.QuizTable.QUESTION_COLUMN + " TEXT, " +
                ReadingQuizContainer.QuizTable.OPTION1_COLUMN + " TEXT, " +
                ReadingQuizContainer.QuizTable.OPTION2_COLUMN + " TEXT, " +
                ReadingQuizContainer.QuizTable.OPTION3_COLUMN + " TEXT, " +
                ReadingQuizContainer.QuizTable.ANS_COLUMN + " INTEGER, " +
                ReadingQuizContainer.QuizTable.COLUMN_CATEGORY_ID + " INTEGER, " +
                "FOREIGN KEY(" + ReadingQuizContainer.QuizTable.COLUMN_CATEGORY_ID + ") REFERENCES " +
                ReadingQuizContainer.CategoriesTable.TABLE_NAME + "(" + ReadingQuizContainer.CategoriesTable._ID + ")" + "ON DELETE CASCADE" +
                " )"

        db.execSQL(CAT_TABLE)
        db.execSQL(QB_TABLE)

        GenerateCategoriesTable()
        GenerateQuestionFunction()

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + ReadingQuizContainer.CategoriesTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ReadingQuizContainer.QuizTable.QUESTION_TABLE_NAME);
        onCreate(db);
    }

    override fun onConfigure(db: SQLiteDatabase?) {
        super.onConfigure(db)
        db?.setForeignKeyConstraintsEnabled(true)
    }

    private fun GenerateCategoriesTable() {
        val c1 = Category()
        addCategory(c1)
        val c2 = Category()
        addCategory(c2)
        val c3 = Category()
        addCategory(c3)
        val c4 = Category()
        addCategory(c4)
    }

    private fun addCategory(category: Category) {
        val cv = ContentValues()
        cv.put(ReadingQuizContainer.CategoriesTable.COLUMN_NAME, category.getName())
        db!!.insert(ReadingQuizContainer.CategoriesTable.TABLE_NAME, null, cv)
    }

    private fun GenerateQuestionFunction() {
        val q1 = ReadingQuestion("In which word would you find a piece of jewellery", "draft", "linking", "ordering", 3, Category.SMALL_IN_BIG_WORDS)
        addQuestion(q1)
        val q2 = ReadingQuestion("In which word would you find something you sail on", "facts", "draft", "sentence", 2, Category.SMALL_IN_BIG_WORDS)
        addQuestion(q2)
        val q3 = ReadingQuestion("In which word would you find a thing that movie stars do", "facts", "sentence", "ordering", 1, Category.SMALL_IN_BIG_WORDS)
        addQuestion(q3)
        val q4 = ReadingQuestion("In which word would you find a number", "sentence", "linking", "draft", 1, Category.SMALL_IN_BIG_WORDS)
        addQuestion(q4)
        val q5 = ReadingQuestion("In which word would you find royalty", "ordering", "linking", "sentence", 2, Category.SMALL_IN_BIG_WORDS)
        addQuestion(q5)
        val q6 = ReadingQuestion("Maria missed the bus this morning ________ she will be late", "so", "because", "if", 1, Category.LINKING_WORDS)
        addQuestion(q6)
        val q7 = ReadingQuestion("The ladder slipped ________ the floor was wet", "so", "because", "if", 2, Category.LINKING_WORDS)
        addQuestion(q7)
        val q8 = ReadingQuestion("Sean will be out of work for three weeks ________ he spained his ankle", "so", "because", "if", 2, Category.LINKING_WORDS)
        addQuestion(q8)
        val q9 = ReadingQuestion("Leave the building immediately ________ you hear the fire alarm", "so", "because", "if", 3, Category.LINKING_WORDS)
        addQuestion(q9)
        val q10 = ReadingQuestion("It is important to wear comfortable shoes ________ you are on your feet all day", "so", "because", "if", 3, Category.LINKING_WORDS)
        addQuestion(q10)
    }

    private fun addQuestion(qb: ReadingQuestion) {
        val contentValues = ContentValues()
        contentValues.put(ReadingQuizContainer.QuizTable.QUESTION_COLUMN, qb.getmQuestion())
        contentValues.put(ReadingQuizContainer.QuizTable.OPTION1_COLUMN, qb.getmOption1())
        contentValues.put(ReadingQuizContainer.QuizTable.OPTION2_COLUMN, qb.getmOption2())
        contentValues.put(ReadingQuizContainer.QuizTable.OPTION3_COLUMN, qb.getmOption3())
        contentValues.put(ReadingQuizContainer.QuizTable.ANS_COLUMN, qb.getmRightAns())
        contentValues.put(ReadingQuizContainer.QuizTable.COLUMN_CATEGORY_ID, qb.getCategoryID())
        db!!.insert(ReadingQuizContainer.QuizTable.QUESTION_TABLE_NAME, null, contentValues)
    }

    fun getAllCategories(): ArrayList<Category>? {
        val categorySet: List<Category>
        db = readableDatabase
        val categoryList = ArrayList<Category>()

        val c = db!!.rawQuery("SELECT * FROM CategoriesTable.TABLE_NAME", null)

        if (c.moveToFirst()) {
            do {
                val category = Category()
                category.setId(c.getInt(c.getColumnIndex(CategoriesTable._ID)))
                category.setName(c.getString(c.getColumnIndex(CategoriesTable.COLUMN_NAME)))
                categoryList.add(category)
                categoryList.add(category)
            } while (c.moveToNext())

        }
        c.close()
        return categoryList

        }

    fun getAllQuestions(): ArrayList<ReadingQuestion>? {
        val questionSet: List<ReadingQuestion>
        db = readableDatabase
        val questionSetsList = ArrayList<ReadingQuestion>()
        val c = db!!.rawQuery("SELECT * FROM QuizTable.QUESTION_TABLE_NAME", null)

        if (c.moveToFirst()) {
            do {
                val question = ReadingQuestion()
                question.setId(c.getInt(c.getColumnIndex(ReadingQuizContainer.QuizTable._ID)))
                question.setmQuestion(c.getString(c.getColumnIndex(ReadingQuizContainer.QuizTable.QUESTION_COLUMN)))
                question.setmOption1(c.getString(c.getColumnIndex(ReadingQuizContainer.QuizTable.OPTION1_COLUMN)))
                question.setmOption2(c.getString(c.getColumnIndex(ReadingQuizContainer.QuizTable.OPTION2_COLUMN)))
                question.setmOption3(c.getString(c.getColumnIndex(ReadingQuizContainer.QuizTable.OPTION3_COLUMN)))
                question.setmRightAns(c.getInt(c.getColumnIndex(ReadingQuizContainer.QuizTable.ANS_COLUMN)))
                question.setCategoryID(c.getInt(c.getColumnIndex(ReadingQuizContainer.QuizTable.COLUMN_CATEGORY_ID)));
                questionSetsList.add(question)
            } while (c.moveToNext())

        }
        c.close()
        return questionSetsList

        }


    fun getQuestions(categoryID: Int): ArrayList<ReadingQuestion>? {
        val questionList: ArrayList<ReadingQuestion> = ArrayList()
        db = readableDatabase
        val selection: String = ReadingQuizContainer.QuizTable.COLUMN_CATEGORY_ID.toString() + " = ? "
        val selectionArgs = arrayOf(categoryID.toString())
        val c = db!!.query(
            ReadingQuizContainer.QuizTable.QUESTION_TABLE_NAME,
            null,
            selection,
            selectionArgs,
            null,
            null,
            null
        )
        if (c.moveToFirst()) {
            do {
                val question = ReadingQuestion()
                question.setId(c.getInt(c.getColumnIndex(ReadingQuizContainer.QuizTable._ID)))
                question.setmQuestion(c.getString(c.getColumnIndex(ReadingQuizContainer.QuizTable.QUESTION_COLUMN)))
                question.setmOption1(c.getString(c.getColumnIndex(ReadingQuizContainer.QuizTable.OPTION1_COLUMN)))
                question.setmOption2(c.getString(c.getColumnIndex(ReadingQuizContainer.QuizTable.OPTION2_COLUMN)))
                question.setmOption3(c.getString(c.getColumnIndex(ReadingQuizContainer.QuizTable.OPTION3_COLUMN)))
                question.setmRightAns(c.getInt(c.getColumnIndex(ReadingQuizContainer.QuizTable.ANS_COLUMN)))
                question.setCategoryID(c.getInt(c.getColumnIndex(ReadingQuizContainer.QuizTable.COLUMN_CATEGORY_ID)))
                questionList.add(question)
            } while (c.moveToNext())
        }
        c.close()
        return questionList
    }


    companion object {

        private val DATABASE_NAME = "ReadingQuiz.db"
        private val DATABASE_VERSION = 1
    }

}
