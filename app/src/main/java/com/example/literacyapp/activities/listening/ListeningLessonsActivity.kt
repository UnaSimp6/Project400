package com.example.literacyapp.activities.listening

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import com.example.literacyapp.R
import com.example.literacyapp.activities.BaseActivity
import com.example.literacyapp.activities.reading.ReadingDetailActivity
import kotlinx.android.synthetic.main.activity_courses.*
import kotlinx.android.synthetic.main.activity_listening_lessons.*
import kotlinx.android.synthetic.main.activity_reading_lessons.*
import kotlinx.android.synthetic.main.activity_writing_lessons.*
import kotlinx.android.synthetic.main.header_reading_item.*


class ListeningLessonsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listening_lessons)

        // This is used to hide the status bar and make the splash screen as a full screen activity.
        window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        setSupportActionBar(toolbar_listening_menu_activity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar_listening_menu_activity.setNavigationOnClickListener {
            onBackPressed()
        }
        setupActionBar()

        btn_listening_lesson.setOnClickListener {
            startActivity(Intent(this@ListeningLessonsActivity, ReadingDetailActivity::class.java))
            finish()
        }
        btn_asking_questions.setOnClickListener {
            startActivity(Intent(this@ListeningLessonsActivity, ReadingDetailActivity::class.java))
            finish()
        }
        btn_following_instructions.setOnClickListener {
            startActivity(Intent(this@ListeningLessonsActivity, ReadingDetailActivity::class.java))
            finish()
        }
        btn_giving_opinions.setOnClickListener {
            startActivity(Intent(this@ListeningLessonsActivity, ReadingDetailActivity::class.java))
            finish()
        }
        btn_communicating.setOnClickListener {
            startActivity(Intent(this@ListeningLessonsActivity, ReadingDetailActivity::class.java))
            finish()
        }

    }

    /**
     * A function for actionBar Setup.
     */
    private fun setupActionBar() {

        setSupportActionBar(toolbar_listening_menu_activity)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_white_color_back_24dp)
        }

        toolbar_listening_menu_activity.setNavigationOnClickListener { onBackPressed() }
    }
}

