package com.example.literacyapp.activities.reading

import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.WindowManager
import com.example.literacyapp.R
import com.example.literacyapp.activities.BaseActivity
import com.example.literacyapp.activities.Utils
import kotlinx.android.synthetic.main.activity_courses.*
import kotlinx.android.synthetic.main.activity_reading_explanation.*
import kotlinx.android.synthetic.main.activity_levels.*
import java.util.*

class ReadingExplanationActivity : BaseActivity(), TextToSpeech.OnInitListener {

    private var tts: TextToSpeech? = null //Variable for Text to Speech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Utils.onActivityCreateSetTheme(this)
        setContentView(R.layout.activity_reading_explanation)



        // This is used to hide the status bar and make the splash screen as a full screen activity.
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        setSupportActionBar(toolbar_reading_explanation_activity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar_reading_explanation_activity.setNavigationOnClickListener {
            onBackPressed()
        }
        setupActionBar()

        //Initialize the Text to Speech
        tts = TextToSpeech(this, this)


        btnSpeakExplan.setOnClickListener { view ->
            speakOut(R.string.quiz_explanation1.toString())
        }

        btn_take_reading_quiz.setOnClickListener {
            startActivity(Intent(this@ReadingExplanationActivity, ReadingQuizActivity::class.java))
            finish()
        }

        btn_skip_reading_quiz.setOnClickListener {
            startActivity(Intent(this@ReadingExplanationActivity, ReadingLessonsActivity::class.java))
            finish()
        }
    }


    private fun speakOut(text: String) {
        val text = getString(R.string.quiz_explanation1)
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

    /**
     * A function for actionBar Setup.
     */
    private fun setupActionBar() {

        setSupportActionBar(toolbar_reading_explanation_activity)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_black_color_back_24dp)
        }

        toolbar_reading_explanation_activity.setNavigationOnClickListener { onBackPressed() }
    }
}