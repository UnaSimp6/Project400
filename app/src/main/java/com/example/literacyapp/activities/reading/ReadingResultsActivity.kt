package com.example.literacyapp.activities.reading

import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import com.example.literacyapp.R
import com.example.literacyapp.activities.BaseActivity
import com.example.literacyapp.activities.CoursesActivity
import com.example.literacyapp.model.Utils
import com.example.literacyapp.adapters.SqliteOpenHelper
import com.example.literacyapp.utils.ReadingQuestions
import kotlinx.android.synthetic.main.activity_results.*
import java.text.SimpleDateFormat
import java.util.*

class ReadingResultsActivity : BaseActivity(), TextToSpeech.OnInitListener{

    private var tts: TextToSpeech? = null //Variable for Text to Speech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Utils.onActivityCreateSetTheme(this)
        setContentView(R.layout.activity_results)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN


        val username = intent.getStringExtra(ReadingQuestions.USER_READING_NAME)
        //tv_name.text = username

        val totalReadingQuestions = intent.getIntExtra(ReadingQuestions.TOTAL_READING_QUESTIONS, 0)
        val correctReadingAnswer = intent.getIntExtra(ReadingQuestions.CORRECT_READING_ANSWERS, 0)

        tv_score.text = "Your score is $correctReadingAnswer out of $totalReadingQuestions"

        //Initialize the Text to Speech
        tts = TextToSpeech(this, this)


        btnSpeak.setOnClickListener { view ->
            speakOut(R.string.result_text.toString())
        }

        btn_continue.setOnClickListener {
                startActivity(Intent(this, ReadingLessonsActivity::class.java))
                finish()
            }

        btn_skip.setOnClickListener {
                startActivity(Intent(this, CoursesActivity::class.java))
            finish()
        }

        addDateToDatabase()
    }


    private fun speakOut(text: String) {
        val text = getString(R.string.result_text)
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

    override fun onDestroy() {

        if(tts != null){
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()

    }


    private fun addDateToDatabase() {

        val c = Calendar.getInstance() // Calender Current Instance
        val dateTime = c.time // Current Date and Time of the system.
        Log.e("Date : ", "" + dateTime) // Printed in the log.

        /**
         * Here we have taken an instance of Date Formatter as it will format our
         * selected date in the format which we pass it as an parameter and Locale.
         * Here I have passed the format as dd MMM yyyy HH:mm:ss.
         *
         * The Locale : Gets the current value of the default locale for this instance
         * of the Java Virtual Machine.
         */
        val sdf = SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.getDefault()) // Date Formatter
        val date = sdf.format(dateTime) // dateTime is formatted in the given format.
        Log.e("Formatted Date : ", "" + date) // Formatted date is printed in the log.

        // Instance of the Sqlite Open Helper class.
        val dbHandler = SqliteOpenHelper(this, null)
        dbHandler.addDate(date) // Add date function is called.
        Log.e("Date : ", "Added...") // Printed in log which is printed if the complete execution is done.
    }
}
