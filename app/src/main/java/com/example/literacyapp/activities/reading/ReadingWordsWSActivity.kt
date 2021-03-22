package com.example.literacyapp.activities.reading

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View.VISIBLE
import android.view.WindowManager
import android.widget.Toast
import com.example.literacyapp.R
import com.example.literacyapp.activities.BaseActivity
import com.example.literacyapp.activities.CoursesActivity
import com.example.literacyapp.activities.Utils
import com.example.literacyapp.utils.Constants
import com.example.literacyapp.utils.Constants.LESSONS_RESPONSE_DATA
import com.example.literacyapp.utils.Constants.WORKSHEET_RESPONSE_DATA
import kotlinx.android.synthetic.main.activity_reading_words_ws.*
import kotlinx.android.synthetic.main.activity_results.*

class ReadingWordsWSActivity :  BaseActivity() {
    val TAG = "Reading Worksheet"

    private var isBackground = true

    private lateinit var mSharedPreference: SharedPreferences
    private var  lessonCompleted = 0
    private var onBackPressedTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Utils.onActivityCreateSetTheme(this)
        setContentView(R.layout.activity_reading_words_ws)

        // This is used to hide the status bar and make the splash screen as a full screen activity.
        window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setSupportActionBar(toolbar_reading_worksheet_activity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar_reading_worksheet_activity.setNavigationOnClickListener {
            onBackPressed()
        }
        setupActionBar()

        mSharedPreference = getSharedPreferences(Constants.PREFERENCE_NAME, Context.MODE_PRIVATE)

        btn_small_in_big_words.setOnClickListener {
            //startActivity(Intent(this@ReadingWordsWSActivity, MainReadingQuiz::class.java))
            //finish()

            val intent = Intent(this, MainReadingQuiz::class.java)
            intent.putExtra(Constants.LESSONS_RESPONSE_DATA, Constants.LESSONCOMPLETED)
            setResult(Activity.RESULT_OK, intent)
            startActivityForResult(intent, READINGWORDS_REQUEST_CODE)
        }

        btn_linking_words.setOnClickListener {
            startActivity(Intent(this@ReadingWordsWSActivity, MainReadingQuiz::class.java))
            finish()
        }

        btn_common_abbr.setOnClickListener {
            startActivity(Intent(this@ReadingWordsWSActivity, MainReadingQuiz::class.java))
            finish()
        }

        btn_same_sounding_words.setOnClickListener {
            startActivity(Intent(this@ReadingWordsWSActivity, MainReadingQuiz::class.java))
            finish()
        }

    }
    private fun passReadinWSProgress(){
       // var lessonCompleted = 0
        if(tv_completedSmallWords.text == "Completed") {
            lessonCompleted++
        }
        if(tv_completedLinkingWords.text == "Completed"){
            lessonCompleted++
        }
        if(tv_completedSoundingWords.text == "Completed"){
            lessonCompleted++
        }
        if(tv_completedCommonAbbr.text == "Completed"){
            lessonCompleted++
        }
        val editor = mSharedPreference.edit()
        editor.putInt(Constants.LESSONS_RESPONSE_DATA, lessonCompleted)
        editor.apply()

        }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == READINGWORDS_REQUEST_CODE) {
            if(resultCode == Activity.RESULT_OK){
                finish()
            }
        }
    }
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
        val tscore = mSharedPreference.getInt(WORKSHEET_RESPONSE_DATA,0)
        if(tscore > 0 ) {
                tv_completedSmallWords.text = "Completed"
                tv_completedSmallWords.visibility = VISIBLE
                passReadinWSProgress()

        }

        isBackground = true
    }
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
        //if (isBackground) {
            //
            //val tscore = mSharedPreference.getInt(WORKSHEET_RESPONSE_DATA,0)
            val lessonCompleted = mSharedPreference.getInt(LESSONS_RESPONSE_DATA, Constants.LESSONCOMPLETED)
            //var lessonCompleted = 0
            if(lessonCompleted > 0) {
                //passReadinWSProgress()
                val editor = mSharedPreference.edit()
                editor.putInt(Constants.LESSONS_RESPONSE_DATA, lessonCompleted)
                editor.apply()
            }

    //}
    }
    override fun onRestart() {
        super.onRestart()
            val tscore = mSharedPreference.getInt(WORKSHEET_RESPONSE_DATA,0)
            if(tscore > 0 ) {
                tv_completedSmallWords.text = "Completed"
                tv_completedSmallWords.visibility = VISIBLE
            }
        isBackground = true
    }
    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")

    }
    override fun onBackPressed() {

        if (onBackPressedTime + 2000 > System.currentTimeMillis()) {
           // passReadinWSProgress()
            val intent = Intent(this, ReadingDetailActivity::class.java)
            intent.putExtra(Constants.LESSONS_RESPONSE_DATA, lessonCompleted)
            setResult(Activity.RESULT_OK, intent)
            startActivityForResult(intent, READINGWORDS_REQUEST_CODE)

        } else {
            Toast.makeText(this, "Press Back Again", Toast.LENGTH_SHORT).show()
        }
        onBackPressedTime = System.currentTimeMillis()

    }

    /**
     * A function for actionBar Setup.
     */
    private fun setupActionBar() {

        setSupportActionBar(toolbar_reading_worksheet_activity)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_black_color_back_24dp)
        }

        toolbar_reading_worksheet_activity.setNavigationOnClickListener { onBackPressed() }
    }

    companion object {
        val WS_COMPLETED = "WorksheetsCompleted"
        private val READINGWORDS_REQUEST_CODE = 0
        private var counter = 1
    }
}