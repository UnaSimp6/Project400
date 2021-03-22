package com.example.literacyapp.activities.reading

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.example.literacyapp.R
import com.example.literacyapp.activities.BaseActivity
import com.example.literacyapp.activities.Utils
import com.example.literacyapp.utils.Constants
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import kotlinx.android.synthetic.main.activity_courses.*
import kotlinx.android.synthetic.main.activity_reading_explanation.*
import kotlinx.android.synthetic.main.activity_reading_detail.*
import kotlinx.android.synthetic.main.activity_reading_detail.btnSpeak
import kotlinx.android.synthetic.main.activity_reading_words_ws.*
import kotlinx.android.synthetic.main.activity_results.*
import java.util.*

class ReadingDetailActivity :  YouTubeBaseActivity() , TextToSpeech.OnInitListener {

    private var tts: TextToSpeech? = null //Variable for Text to Speech
    private var onBackPressedTime: Long = 0

    companion object {
        val VIDEO_ID1: String = "WKwRWHAVs1E";
        val VIDEO_ID2: String = "xJzSQJjlRhg"
        val YOUTUBE_API_KEY: String = "AIzaSyAT5cKA85zclLcq6rGcOitM9jwXuD4tpcA"
    }
    private lateinit var mSharedPreference: SharedPreferences

    lateinit var youtubePlayerInit: YouTubePlayer.OnInitializedListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Utils.onActivityCreateSetTheme(this)
        setContentView(R.layout.activity_reading_detail)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        //setSupportActionBar(toolbar_Reading_Detail_activity)
        //supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //toolbar_Reading_Detail_activity.setNavigationOnClickListener {
        //    onBackPressed()
       // }
        //setupActionBar()

        mSharedPreference = getSharedPreferences(Constants.PREFERENCE_NAME, Context.MODE_PRIVATE)

        //Initialize the Text to Speech
        tts = TextToSpeech(this, this)

        btnSpeak.setOnClickListener { view ->
            speakOut(R.string.reading_detail_heading.toString())
        }

        initUI()
        startLesson()
    }

    private fun startLesson(){
        btn_start_lesson.setOnClickListener {
            startActivity(Intent(this@ReadingDetailActivity, ReadingWordsWSActivity::class.java))
            finish()
        }
    }
    private fun speakOut(text: String) {
        val text = getString(R.string.reading_detail_heading)
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

    private fun initUI() {
        youtubePlayerInit = object : YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, youtubePlayer: YouTubePlayer?, p2: Boolean) {
                youtubePlayer?.loadVideo(VIDEO_ID1)
                youtubePlayer?.loadVideo(VIDEO_ID2)
            }

            override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {
                Toast.makeText(applicationContext, "Something went wrong !! ", Toast.LENGTH_SHORT).show()
            }
        }


        youtubePlayer1.setOnClickListener(View.OnClickListener { v ->
            youtubePlayer1.initialize(YOUTUBE_API_KEY, youtubePlayerInit)
        })


        youtubePlayer2.setOnClickListener(View.OnClickListener { v ->
            youtubePlayer2.initialize(YOUTUBE_API_KEY, youtubePlayerInit)
        })
        /*btnPlay2.setOnClickListener(View.OnClickListener { v ->
            youtubePlayer2.initialize(YOUTUBE_API_KEY, youtubePlayerInit)
        })*/

        //btnPlay3.setOnClickListener(View.OnClickListener { v ->
        //    youtubePlayer3.initialize(YOUTUBE_API_KEY, youtubePlayerInit)
       // })
    }

    /**
     * A function for actionBar Setup.
     */
    //private fun setupActionBar() {

    /*    setSupportActionBar(toolbar_Reading_Detail_activity)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_black_color_back_24dp)
        }


        toolbar_Reading_Detail_activity.setNavigationOnClickListener { onBackPressed() }
    }*/

    override fun onBackPressed() {

        if (onBackPressedTime + 2000 > System.currentTimeMillis()) {
            startActivity(Intent(this, ReadingLessonsActivity::class.java))
            finish()
            //passReadinWSProgress()
            //val intent = Intent(this, ReadingDetailActivity::class.java)
            //intent.putExtra(Constants.LESSONS_RESPONSE_DATA, lessonCompleted)
            //setResult(Activity.RESULT_OK, intent)
            //startActivityForResult(intent, ReadingWordsWSActivity.READINGWORDS_REQUEST_CODE)

        } else {
            Toast.makeText(this, "Press Back Again", Toast.LENGTH_SHORT).show()
        }
        onBackPressedTime = System.currentTimeMillis()

    }
}