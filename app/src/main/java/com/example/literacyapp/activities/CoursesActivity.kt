package com.example.literacyapp.activities

import android.content.Intent
import android.os.Bundle
import android.provider.Settings.Global.putInt
import android.util.Log
import android.view.WindowManager
import com.example.literacyapp.R
import com.example.literacyapp.activities.communications.CommunicationsExplanationActivity
import com.example.literacyapp.activities.listening.ListeningExplanationActivity
import com.example.literacyapp.activities.reading.ReadingExplanationActivity
import com.example.literacyapp.activities.writing.WritingExplanationActivity
import kotlinx.android.synthetic.main.activity_courses.*
import kotlinx.android.synthetic.main.activity_levels.*

class CoursesActivity :  BaseActivity() {

    val TAG = "Course 1"

    private var tutorialUsed = false
    private var tutorialPage = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Utils.onActivityCreateSetTheme(this)

        setContentView(R.layout.activity_courses)

        tutorialUsed = false
        tutorialPage = 1

        init()

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

        btn_reading.setOnClickListener {
            startActivity(Intent(this@CoursesActivity, ReadingExplanationActivity::class.java))
            finish()
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

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
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

    /** do your startup tasks here  */
    fun init() {
        if (!tutorialUsed) {
            showTutorial(tutorialPage)
        }
    }


    fun showTutorial(tutorialPage: Int) {
        Utils.onActivityCreateSetTheme(this)

        setContentView(R.layout.activity_courses)

    }
    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart")

    }
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
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
}