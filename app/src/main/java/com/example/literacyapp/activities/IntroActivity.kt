package com.example.literacyapp.activities

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.example.literacyapp.R
import kotlinx.android.synthetic.main.activity_intro.*


class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        // This is used to hide the status bar and make the splash screen as a full screen activity.
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        // This is used to get the file from the assets folder and set it to the title textView.
       /* val typeface: Typeface =
            Typeface.createFromAsset(assets, "carbon bl.ttf")
        tv_app_name_intro.typeface = typeface*/

        btn_sign_in_intro.setOnClickListener {

            // Launch the sign in screen.
            startActivity(Intent(this@IntroActivity, SignInActivity::class.java))
        }

        btn_sign_up_intro.setOnClickListener {

            // Launch the sign up screen.
            startActivity(Intent(this@IntroActivity, SignUpActivity::class.java))
        }
    }

    }