package com.example.literacyapp.activities.writing

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import com.example.literacyapp.R
import com.example.literacyapp.activities.BaseActivity
import com.example.literacyapp.activities.reading.ReadingDetailActivity
import kotlinx.android.synthetic.main.activity_courses.*
import kotlinx.android.synthetic.main.activity_reading_lessons.*
import kotlinx.android.synthetic.main.activity_writing_lessons.*
import kotlinx.android.synthetic.main.header_reading_item.*


class WritingLessonsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_writing_lessons)

        // This is used to hide the status bar and make the splash screen as a full screen activity.
        window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        setSupportActionBar(toolbar_reading_menu_activity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar_writing_menu_activity.setNavigationOnClickListener {
            onBackPressed()
        }
        setupActionBar()

        btn_writing_notes.setOnClickListener {
            startActivity(Intent(this@WritingLessonsActivity, ReadingDetailActivity::class.java))
            finish()
        }
        btn_writing_sentences.setOnClickListener {
            startActivity(Intent(this@WritingLessonsActivity, ReadingDetailActivity::class.java))
            finish()
        }
        btn_writing_different_styles.setOnClickListener {
            startActivity(Intent(this@WritingLessonsActivity, ReadingDetailActivity::class.java))
            finish()
        }
        btn_handwriting.setOnClickListener {
            startActivity(Intent(this@WritingLessonsActivity, ReadingDetailActivity::class.java))
            finish()
        }

    }

    /**
     * A function for actionBar Setup.
     */
    private fun setupActionBar() {

        setSupportActionBar(toolbar_writing_menu_activity)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_black_color_back_24dp)
        }

        toolbar_writing_menu_activity.setNavigationOnClickListener { onBackPressed() }
    }
}

