package com.example.literacyapp.activities.communications

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.example.literacyapp.R
import kotlinx.android.synthetic.main.activity_communications_explanation.*

class CommunicationsExplanationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_communications_explanation)

        // This is used to hide the status bar and make the splash screen as a full screen activity.
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        setSupportActionBar(toolbar_communications_explanation_activity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar_communications_explanation_activity.setNavigationOnClickListener {
            onBackPressed()
        }
        setupActionBar()

        btn_take_communications_quiz.setOnClickListener {
            startActivity(Intent(this@CommunicationsExplanationActivity, CommunicationsQuizActivity::class.java))

        }
    }

    /**
     * A function for actionBar Setup.
     */
    private fun setupActionBar() {

        setSupportActionBar(toolbar_communications_explanation_activity)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_black_color_back_24dp)
        }

        toolbar_communications_explanation_activity.setNavigationOnClickListener { onBackPressed() }
    }
}