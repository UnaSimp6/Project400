package com.example.literacyapp.activities.writing

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import com.example.literacyapp.R
import com.example.literacyapp.activities.BaseActivity
import kotlinx.android.synthetic.main.activity_writing_explanation.*

class WritingExplanationActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_writing_explanation)
        // This is used to hide the status bar and make the splash screen as a full screen activity.
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        setSupportActionBar(toolbar_writing_explanation_activity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar_writing_explanation_activity.setNavigationOnClickListener {
            onBackPressed()
        }
        setupActionBar()

        btn_take_writing_quiz.setOnClickListener {
            startActivity(Intent(this@WritingExplanationActivity, WritingQuizActivity::class.java))
            finish()

        }
    }

    /**
     * A function for actionBar Setup.
     */
    private fun setupActionBar() {

        setSupportActionBar(toolbar_writing_explanation_activity)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_black_color_back_24dp)
        }

        toolbar_writing_explanation_activity.setNavigationOnClickListener { onBackPressed() }
    }
}