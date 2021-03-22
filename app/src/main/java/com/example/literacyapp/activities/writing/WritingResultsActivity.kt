package com.example.literacyapp.activities.writing

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.literacyapp.R
import com.example.literacyapp.activities.BaseActivity
import com.example.literacyapp.activities.CoursesActivity
import com.example.literacyapp.activities.reading.ReadingLessonsActivity
import com.example.literacyapp.utils.WritingQuestions
import kotlinx.android.synthetic.main.activity_results.*

class WritingResultsActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN


        val username = intent.getStringExtra(WritingQuestions.USER_WRITING_NAME)
        //tv_name.text = username

        val totalWritingQuestions = intent.getIntExtra(WritingQuestions.TOTAL_WRITING_QUESTIONS, 0)
        val correctWritingAnswer = intent.getIntExtra(WritingQuestions.CORRECT_WRITING_ANSWERS, 0)

        tv_score.text = "Your score is $correctWritingAnswer out of $totalWritingQuestions"


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
