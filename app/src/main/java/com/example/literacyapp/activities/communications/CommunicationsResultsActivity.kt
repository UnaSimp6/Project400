package com.example.literacyapp.activities.communications

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.literacyapp.R
import com.example.literacyapp.activities.BaseActivity
import com.example.literacyapp.activities.CoursesActivity
import com.example.literacyapp.activities.reading.ReadingLessonsActivity
import com.example.literacyapp.utils.CommunicationsQuestions
import kotlinx.android.synthetic.main.activity_results.*

class CommunicationsResultsActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN


        val username = intent.getStringExtra(CommunicationsQuestions.USER_NAME)
        //tv_name.text = username

        val totalCommunicationsQuestions = intent.getIntExtra(CommunicationsQuestions.TOTAL_COMMUNICATIONS_QUESTIONS, 0)
        val correctCommunicationsAnswer = intent.getIntExtra(CommunicationsQuestions.CORRECT_COMMUNICATIONS_ANSWERS, 0)

        tv_score.text = "Your score is $correctCommunicationsAnswer out of $totalCommunicationsQuestions"

        btn_continue.setOnClickListener {
            startActivity(Intent(this, ReadingLessonsActivity::class.java))
            finish()
        }

        btn_skip.setOnClickListener {
            startActivity(Intent(this, CoursesActivity::class.java))
            finish()
        }
    }
}
