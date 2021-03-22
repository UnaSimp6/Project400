package com.example.literacyapp.activities
import android.app.Activity
import android.content.Intent
import com.example.literacyapp.R
import kotlinx.android.synthetic.main.activity_courses.*

object Utils {
    private var sTheme = 0
    const val THEME_LEVEL1 = 1
    const val THEME_LEVEL2 = 2
    const val THEME_LEVEL3 = 3


    /**
     * Set the theme of the Activity, and restart it by creating a new Activity of the same type.
     */
    fun changeToTheme(activity: Activity, theme: Int) {
        sTheme = theme
        activity.finish()
        activity.startActivity(Intent(activity, activity::class.java))
    }

    /** Set the theme of the activity, according to the configuration.  */
    fun onActivityCreateSetTheme(activity: Activity) {
        when (sTheme) {
            THEME_LEVEL1 -> activity.setTheme(R.style.FirstTheme)
            THEME_LEVEL2 -> activity.setTheme(R.style.SecondTheme)
            THEME_LEVEL3 -> activity.setTheme(R.style.ThirdTheme)
            else -> activity.setTheme(R.style.DefaultTheme)
        }

    }

}
