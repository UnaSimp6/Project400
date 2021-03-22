package com.example.literacyapp.activities.reading

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import com.example.literacyapp.R
import com.example.literacyapp.activities.BaseActivity
import com.example.literacyapp.activities.CoursesActivity
import com.example.literacyapp.activities.Utils
import com.example.literacyapp.model.CoursesQuiz
import com.example.literacyapp.utils.Constants
import com.example.literacyapp.utils.ReadingQuestions
import kotlinx.android.synthetic.main.activity_courses.*
import kotlinx.android.synthetic.main.activity_reading_lessons.*
import kotlinx.android.synthetic.main.activity_reading_quiz.*
import kotlinx.android.synthetic.main.activity_reading_words_ws.*
import kotlinx.android.synthetic.main.header_reading_item.*

//const val READING_ID = "reading id"

class ReadingLessonsActivity : BaseActivity() {
    val TAG = "Course 1"
    private var mCurrentPosition: Int = 1 // Default and the first question position
    private var mWorksheetList: TextView? = null
    private var mCorrectAnswers: Int = 0

    private var isBackground = true

    private lateinit var mSharedPreference: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Utils.onActivityCreateSetTheme(this)
        setContentView(R.layout.activity_reading_lessons)

        // This is used to hide the status bar and make the splash screen as a full screen activity.
        window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        setSupportActionBar(toolbar_reading_menu_activity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar_reading_menu_activity.setNavigationOnClickListener {
            onBackPressed()
        }
        setupActionBar()

        mSharedPreference = getSharedPreferences(Constants.PREFERENCE_NAME, Context.MODE_PRIVATE)

        var lessonCompleted: Int = mSharedPreference.getInt(Constants.LESSONS_RESPONSE_DATA,0)

        if(intent.hasExtra(ReadingWordsWSActivity.WS_COMPLETED)){
            if(lessonCompleted > 0 ) {
            progressBar.progress = lessonCompleted
            tv_progress.text = "$lessonCompleted" + "/" + progressReadingWords.getMax()
        }
        }

        btn_reading_words.setOnClickListener {
            startActivity(Intent(this@ReadingLessonsActivity, ReadingDetailActivity::class.java))
            finish()
        }
        btn_using_rules.setOnClickListener {
            startActivity(Intent(this@ReadingLessonsActivity, ReadingDetailActivity::class.java))
            finish()
        }
        btn_reading_text.setOnClickListener {
            startActivity(Intent(this@ReadingLessonsActivity, ReadingDetailActivity::class.java))
            finish()
        }
        btn_find_info.setOnClickListener {
            startActivity(Intent(this@ReadingLessonsActivity, ReadingDetailActivity::class.java))
            finish()
        }
        btn_reading_strategy.setOnClickListener {
            startActivity(Intent(this@ReadingLessonsActivity, ReadingDetailActivity::class.java))
            finish()
        }
    }
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
        val lessonCompleted = mSharedPreference.getInt(Constants.LESSONS_RESPONSE_DATA, Constants.LESSONCOMPLETED)
        //if(intent.hasExtra(ReadingWordsWSActivity.WS_COMPLETED)){
            if(lessonCompleted > 0 ) {
                progressReadingWords.progress = lessonCompleted
                tv_progressReadingWords.text = "$lessonCompleted" + "/" + progressReadingWords.getMax()
             }
      //  }
        //isBackground = true

    }
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
        val lessonCompleted = mSharedPreference.getInt(Constants.LESSONS_RESPONSE_DATA, Constants.LESSONCOMPLETED)

        if(lessonCompleted > 0 ) {
           // if(intent.hasExtra(ReadingWordsWSActivity.WS_COMPLETED)){
                progressReadingWords.progress = lessonCompleted
                tv_progressReadingWords.text = "$lessonCompleted" + "/" + progressReadingWords.getMax()
            }
       // }
        //isBackground = true
    }
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
        if (isBackground) {
            val lessonCompleted = mSharedPreference.getInt(Constants.LESSONS_RESPONSE_DATA, Constants.LESSONCOMPLETED)
            val editor = mSharedPreference.edit()
            editor.putInt(Constants.LESSONS_RESPONSE_DATA, lessonCompleted)
            editor.apply()
        }
    }
    override fun onRestart() {
        super.onRestart()
        var lessonCompleted: Int = mSharedPreference.getInt(Constants.LESSONS_RESPONSE_DATA,Constants.LESSONCOMPLETED)
        if(intent.hasExtra(ReadingWordsWSActivity.WS_COMPLETED)){
            if(lessonCompleted > 0 ) {
                progressReadingWords.progress = lessonCompleted
                tv_progressReadingWords.text = "$lessonCompleted" + "/" + progressReadingWords.getMax()
        }
        }
       // isBackground = true
    }
    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")

    }
    /**
     * A function for actionBar Setup.
     */
    private fun setupActionBar() {

        setSupportActionBar(toolbar_reading_menu_activity)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_black_color_back_24dp)
        }

        toolbar_reading_menu_activity.setNavigationOnClickListener { onBackPressed() }
    }
}

