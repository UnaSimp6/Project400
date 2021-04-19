package com.example.literacyapp.activities.reading

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import com.example.literacyapp.R
import com.example.literacyapp.activities.BaseActivity
import com.example.literacyapp.activities.CoursesActivity
import com.example.literacyapp.model.Utils
import com.example.literacyapp.utils.Constants
import kotlinx.android.synthetic.main.activity_courses.*
import kotlinx.android.synthetic.main.activity_reading_detail.*
import kotlinx.android.synthetic.main.activity_reading_lessons.*
import kotlinx.android.synthetic.main.activity_reading_lessons.btnSpeak
import kotlinx.android.synthetic.main.activity_reading_quiz.*
import kotlinx.android.synthetic.main.activity_reading_words_ws.*
import kotlinx.android.synthetic.main.header_reading_item.*
import java.util.*

//const val READING_ID = "reading id"

class ReadingLessonsActivity : BaseActivity(), TextToSpeech.OnInitListener {
    val TAG = "Reading Lesson 1"
    private var mCurrentPosition: Int = 1 // Default and the first question position
    private var mWorksheetList: TextView? = null
    private var mCorrectAnswers: Int = 0

    private var tts: TextToSpeech? = null //Variable for Text to Speech

    private var isBackground = true

    private var  courseCompleted = 0
    private var onBackPressedTime: Long = 0

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

        //Initialize the Text to Speech
        tts = TextToSpeech(this, this)

        btnSpeak.setOnClickListener { view ->
            speakOut(R.string.sound_lessons.toString())
        }


        mSharedPreference = getSharedPreferences(Constants.PREFERENCE_NAME, Context.MODE_PRIVATE)

        var lessonCompleted: Int = mSharedPreference.getInt(Constants.LESSONS_RESPONSE_DATA,0)

        if(intent.hasExtra(ReadingWordsWSActivity.WS_COMPLETED)){
            if(lessonCompleted > 0 ) {
            progressBar.progress = lessonCompleted
            tv_progressReadingWords.text = "$lessonCompleted" + "/" + progressReadingWords.getMax()
        }
        }

        btn_reading_words.setOnClickListener {
            //startActivity(Intent(this@ReadingLessonsActivity, ReadingDetailActivity::class.java))
            //finish()

            val intent = Intent(this, ReadingDetailActivity::class.java)
            intent.putExtra(Constants.COURSES_RESPONSE_DATA, Constants.COURSESCOMPLETED)
            setResult(Activity.RESULT_OK, intent)
            startActivityForResult(intent, LESSONS_REQUEST_CODE)
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

    private fun passLessonsProgress(){
        val lessonCompleted = mSharedPreference.getInt(Constants.LESSONS_RESPONSE_DATA, Constants.LESSONCOMPLETED)
        val courseCompleted = lessonCompleted.toInt()
        val editor = mSharedPreference.edit()
        editor.putInt(Constants.COURSES_RESPONSE_DATA, courseCompleted)
        editor.apply()

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == LESSONS_REQUEST_CODE) {
            if(resultCode == Activity.RESULT_OK){
                finish()
            }
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
            passLessonsProgress()
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
            actionBar.setHomeAsUpIndicator(R.drawable.ic_white_color_back_24dp)
        }

        toolbar_reading_menu_activity.setNavigationOnClickListener { onBackPressed() }
    }

    override fun onBackPressed() {

        if (onBackPressedTime + 2000 > System.currentTimeMillis()) {
            // passReadinWSProgress()
            //   val intent = Intent(this, ReadingDetailActivity::class.java)
            //   intent.putExtra(Constants.LESSONS_RESPONSE_DATA, lessonCompleted)
            //   setResult(Activity.RESULT_OK, intent)
            //  startActivityForResult(intent, READINGWORDS_REQUEST_CODE)
            startActivity(Intent(this, CoursesActivity::class.java))
            finish()

        } else {
            Toast.makeText(this, "Press Back Again", Toast.LENGTH_SHORT).show()
        }
        onBackPressedTime = System.currentTimeMillis()

    }

    companion object {
        val LESSONS_COMPLETED = "LessonsCompleted"
        private val LESSONS_REQUEST_CODE = 0

    }

    private fun speakOut(text: String) {
        val text = getString(R.string.sound_lessons)
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }
    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            // set US English as language for tts
            val result = tts!!.setLanguage(Locale.US)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "The Language specified is not supported!")

            }
        } else {
            Log.e("TTS", "Initialization Failed!")
        }
    }
}

