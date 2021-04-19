package com.example.literacyapp.activities.reading

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View.VISIBLE
import android.view.WindowManager
import android.widget.Toast
import com.example.literacyapp.R
import com.example.literacyapp.activities.BaseActivity
import com.example.literacyapp.model.Utils
import com.example.literacyapp.data.Category
import com.example.literacyapp.utils.Constants
import com.example.literacyapp.utils.Constants.LESSONS_RESPONSE_DATA
import com.example.literacyapp.utils.Constants.LINKCOUNT
import com.example.literacyapp.utils.Constants.SMALLCOUNT
import kotlinx.android.synthetic.main.activity_reading_detail.*
import kotlinx.android.synthetic.main.activity_reading_words_ws.*
import kotlinx.android.synthetic.main.activity_results.*
import java.lang.reflect.Array.set
import java.util.*


class ReadingWordsWSActivity :  BaseActivity(), TextToSpeech.OnInitListener{
    val TAG = "Reading Worksheet"

    private var tts: TextToSpeech? = null //Variable for Text to Speech
    private var isBackground = true

    private lateinit var mSharedPreference: SharedPreferences
    private lateinit var nSharedPreference: SharedPreferences
    private lateinit var oSharedPreference: SharedPreferences

    private var  lessonCompleted = 0
    private var  smallcount = 0
    //private var  linkcount = 0
    private var onBackPressedTime: Long = 0
    val EXTRA_CATEGORY_ID = "extraCategoryID"



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


        //Initialize the Text to Speech
        tts = TextToSpeech(this, this)


        // loadCategories()

        btnSpeakws1.setOnClickListener { view ->
            speakOut1(R.string.small_in_big_words_text.toString())
        }
        btnSpeakws2.setOnClickListener { view ->
            speakOut2(R.string.linking_words_text.toString())
        }
        btnSpeakws3.setOnClickListener { view ->
            speakOut3(R.string.same_sounding_words_text.toString())
        }
        btnSpeakws4.setOnClickListener { view ->
            speakOut4(R.string.common_abbr_text.toString())
        }


        mSharedPreference = getSharedPreferences(Constants.PREFERENCE_NAME, Context.MODE_PRIVATE)
        nSharedPreference = getSharedPreferences(Constants.LINKCOUNT, Context.MODE_PRIVATE)
        oSharedPreference = getSharedPreferences(Constants.SMALLCOUNT, Context.MODE_PRIVATE)



        btn_small_in_big_words.setOnClickListener {
            val categoryID: Int = Category.SMALL_IN_BIG_WORDS



            var  smallcount = 1
            var editor = oSharedPreference.edit()

            editor.putInt(Constants.SMALLCOUNT, smallcount)
            editor.apply()

            val intent = Intent(this, MainReadingQuiz::class.java)

            intent.putExtra(Constants.LESSONS_RESPONSE_DATA, Constants.LESSONCOMPLETED)
            intent.putExtra(EXTRA_CATEGORY_ID, categoryID)
            intent.putExtra(Constants.SMALLCOUNT, smallcount)

            setResult(Activity.RESULT_OK, intent)
            startActivityForResult(intent, READINGWORDS_REQUEST_CODE)

        }

        btn_linking_words.setOnClickListener {
            val categoryID: Int = Category.LINKING_WORDS

            //val linkcount = nSharedPreference.getInt(LINKCOUNT, 0)

           // val editor = nSharedPreference.edit()

          //  editor.putInt(Constants.LINKCOUNT, linkcount)
          //  editor.apply()

            val intent = Intent(this, MainReadingQuiz::class.java)
            intent.putExtra(Constants.LESSONS_RESPONSE_DATA, Constants.LESSONCOMPLETED)
            intent.putExtra(EXTRA_CATEGORY_ID, categoryID)
           // intent.putExtra(Constants.LINKCOUNT, linkcount)

            setResult(Activity.RESULT_OK, intent)
            startActivityForResult(intent, READINGWORDS_REQUEST_CODE)
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

    private fun speakOut1(text: String) {
        val text = getString(R.string.small_in_big_words_text)
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }
    private fun speakOut2(text: String) {
        val text = getString(R.string.linking_words_text)
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }
    private fun speakOut3(text: String) {
        val text = getString(R.string.same_sounding_words_text)
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }
    private fun speakOut4(text: String) {
        val text = getString(R.string.common_abbr_text)
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
    private fun passReadinWSProgress(){
        var lessonCompleted = 0
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
        val smallcount = oSharedPreference.getInt(SMALLCOUNT, 0)
        if(smallcount > 0 ) {
            tv_completedSmallWords.text = "Completed"
            tv_completedSmallWords.visibility = VISIBLE
            passReadinWSProgress()
        }
      //  val linkcount = nSharedPreference.getInt(LINKCOUNT, 0)
        //if (linkcount > 0) {
       //     tv_completedLinkingWords.text = "Completed"
        //    tv_completedLinkingWords.visibility = VISIBLE
        //    passReadinWSProgress()
      //  }


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
           // val tscore = mSharedPreference.getInt(WORKSHEET_RESPONSE_DATA,0
            /*if(tscore > 0 ) {
                tv_completedSmallWords.text = "Completed"
                tv_completedSmallWords.visibility = VISIBLE
            }
            if(lessonCompleted >= 1){
                tv_completedLinkingWords.text = "Completed"
                tv_completedLinkingWords.visibility = VISIBLE*/
        val smallcount = mSharedPreference.getInt(SMALLCOUNT, 0)
        //val linkcount = nSharedPreference.getInt(LINKCOUNT, 0)

        if(smallcount > 0 ) {
            tv_completedSmallWords.text = "Completed"
            tv_completedSmallWords.visibility = VISIBLE
            passReadinWSProgress()
        }
       // if (linkcount > 0) {
       //     tv_completedLinkingWords.text = "Completed"
      //      tv_completedLinkingWords.visibility = VISIBLE
       //     passReadinWSProgress()
      //  }

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
         //   val intent = Intent(this, ReadingDetailActivity::class.java)
         //   intent.putExtra(Constants.LESSONS_RESPONSE_DATA, lessonCompleted)
         //   setResult(Activity.RESULT_OK, intent)
          //  startActivityForResult(intent, READINGWORDS_REQUEST_CODE)
            startActivity(Intent(this@ReadingWordsWSActivity, ReadingDetailActivity::class.java))
            finish()

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
            actionBar.setHomeAsUpIndicator(R.drawable.ic_white_color_back_24dp)
        }

        toolbar_reading_worksheet_activity.setNavigationOnClickListener { onBackPressed() }
    }

    companion object {
        val EXTRA_CATEGORY_ID = "extraCategoryID"
        val WS_COMPLETED = "WorksheetsCompleted"
        private val READINGWORDS_REQUEST_CODE = 0
        var counter = 0
    }
}