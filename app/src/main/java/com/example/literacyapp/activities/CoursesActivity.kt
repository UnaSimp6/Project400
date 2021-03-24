package com.example.literacyapp.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.provider.Settings.Global.putInt
import android.util.Log
import android.view.WindowManager
import com.example.literacyapp.R
import com.example.literacyapp.activities.communications.CommunicationsExplanationActivity
import com.example.literacyapp.activities.listening.ListeningExplanationActivity
import com.example.literacyapp.activities.reading.ReadingDetailActivity
import com.example.literacyapp.activities.reading.ReadingExplanationActivity
import com.example.literacyapp.activities.reading.ReadingLessonsActivity
import com.example.literacyapp.activities.reading.ReadingWordsWSActivity
import com.example.literacyapp.activities.writing.WritingExplanationActivity
import com.example.literacyapp.utils.Constants
import kotlinx.android.synthetic.main.activity_courses.*
import kotlinx.android.synthetic.main.activity_levels.*
import kotlinx.android.synthetic.main.activity_reading_lessons.*
import kotlinx.android.synthetic.main.activity_reading_quiz.*

class CoursesActivity :  BaseActivity() {

    val TAG = "Course 1"

   // private var tutorialUsed = false
    //private var tutorialPage = 2

    private var isBackground = true

    private lateinit var mSharedPreference: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Utils.onActivityCreateSetTheme(this)

        setContentView(R.layout.activity_courses)

       // tutorialUsed = false
       // tutorialPage = 1

       // init()

        // This is used to hide the status bar and make the splash screen as a full screen activity.
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setSupportActionBar(toolbar_courses_activity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar_courses_activity.setNavigationOnClickListener {
            onBackPressed()
        }
        setupActionBar()

        mSharedPreference = getSharedPreferences(Constants.PREFERENCE_NAME, Context.MODE_PRIVATE)

        var courseCompleted: Int = mSharedPreference.getInt(Constants.COURSES_RESPONSE_DATA,0)

        if(intent.hasExtra(ReadingLessonsActivity.LESSONS_COMPLETED)) {
            if (courseCompleted > 0) {
                progressCourseReading.progress = courseCompleted
                tv_progressCourseReading.text = "$courseCompleted" + "/" + progressCourseReading.getMax()
            }
        }

        btn_reading.setOnClickListener {
            //startActivity(Intent(this@CoursesActivity, ReadingExplanationActivity::class.java))
            //finish()

            val intent = Intent(this, ReadingExplanationActivity::class.java)
           // intent.putExtra(Constants.COURSES_RESPONSE_DATA, Constants.COURSESCOMPLETED)
            setResult(Activity.RESULT_OK, intent)
            startActivityForResult(intent, COURSES_REQUEST_CODE)
        }
        btn_writing.setOnClickListener {
            startActivity(Intent(this@CoursesActivity, WritingExplanationActivity::class.java))
            finish()
        }
        btn_listening.setOnClickListener {
            startActivity(Intent(this@CoursesActivity, ListeningExplanationActivity::class.java))
            finish()
        }
        btn_communications.setOnClickListener {
            startActivity(Intent(this@CoursesActivity, CommunicationsExplanationActivity::class.java))
            finish()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CoursesActivity.COURSES_REQUEST_CODE) {
            if(resultCode == Activity.RESULT_OK){
                finish()
            }
        }
    }
    /*override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        // save the tutorial page (or something else)
        savedInstanceState.putInt("TutPage", tutorialPage)
        savedInstanceState.putBoolean("tutUsed", tutorialUsed)
        // more additions possible
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        // restore state
        tutorialPage = savedInstanceState.getInt("TutPage")
        tutorialUsed = savedInstanceState.getBoolean("tutUsed")
        init()
    }

    *//** do your startup tasks here  *//*
    fun init() {
        if (!tutorialUsed) {
            showTutorial(tutorialPage)
        }
    }


    fun showTutorial(tutorialPage: Int) {
        Utils.onActivityCreateSetTheme(this)

        setContentView(R.layout.activity_courses)

    }*/


    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart")
        var courseCompleted: Int = mSharedPreference.getInt(Constants.COURSES_RESPONSE_DATA,Constants.COURSESCOMPLETED)
        if(intent.hasExtra(ReadingWordsWSActivity.WS_COMPLETED)){
            if(courseCompleted > 0 ) {

                progressCourseReading.progress = courseCompleted
                tv_progressCourseReading.text = "$courseCompleted" + "/" +  progressCourseReading.getMax()
            }
        }
        // isBackground = true

    }
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")

        val courseCompleted = mSharedPreference.getInt(Constants.COURSES_RESPONSE_DATA, Constants.COURSESCOMPLETED)
        //if(intent.hasExtra(ReadingWordsWSActivity.WS_COMPLETED)){
        if(courseCompleted > 0 ) {
            progressCourseReading.progress = courseCompleted
            tv_progressCourseReading.text = "$courseCompleted" + "/" +  progressCourseReading.getMax()
        }
        //  }
        //isBackground = true
    }
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
        val courseCompleted = mSharedPreference.getInt(Constants.COURSES_RESPONSE_DATA, Constants.COURSESCOMPLETED)

        if(courseCompleted > 0 ) {
            // if(intent.hasExtra(ReadingWordsWSActivity.WS_COMPLETED)){
            progressCourseReading.progress = courseCompleted
            tv_progressCourseReading.text = "$courseCompleted" + "/" +  progressCourseReading.getMax()
        }
        // }
        //isBackground = true
    }
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
        if (isBackground) {
            val courseCompleted = mSharedPreference.getInt(Constants.COURSES_RESPONSE_DATA, Constants.COURSESCOMPLETED)
            val editor = mSharedPreference.edit()
            editor.putInt(Constants.COURSES_RESPONSE_DATA, courseCompleted)
            editor.apply()
        }
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

        setSupportActionBar(toolbar_courses_activity)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_black_color_back_24dp)
        }

        toolbar_courses_activity.setNavigationOnClickListener { onBackPressed() }
    }

    companion object {
        val COURSES_COMPLETED = "CoursesCompleted"
        private val COURSES_REQUEST_CODE = 0
    }
}