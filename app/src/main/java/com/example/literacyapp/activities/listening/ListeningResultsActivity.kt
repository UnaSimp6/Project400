package com.example.literacyapp.activities.listening

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.literacyapp.R
import com.example.literacyapp.activities.BaseActivity
import com.example.literacyapp.activities.CoursesActivity
import com.example.literacyapp.activities.reading.ReadingLessonsActivity
import com.example.literacyapp.utils.ListeningQuestions
import kotlinx.android.synthetic.main.activity_results.*

class ListeningResultsActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN


        val username = intent.getStringExtra(ListeningQuestions.USER_NAME)
        //tv_name.text = username

        val totalListeningQuestions = intent.getIntExtra(ListeningQuestions.TOTAL_LISTENING_QUESTIONS, 0)
        val correctListeningAnswer = intent.getIntExtra(ListeningQuestions.CORRECT_LISTENING_ANSWERS, 0)

        tv_score.text = "Your score is $correctListeningAnswer out of $totalListeningQuestions"

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
