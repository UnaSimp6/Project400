package com.example.literacyapp.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.MenuItem
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.GravityCompat
import com.bumptech.glide.Glide
import com.example.literacyapp.R
import com.example.literacyapp.firebase.FirestoreClass
import com.example.literacyapp.model.User
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_courses.*
import kotlinx.android.synthetic.main.activity_history.*
import kotlinx.android.synthetic.main.activity_levels.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.nav_header_main.*
import java.util.*


class MainActivity: BaseActivity(), TextToSpeech.OnInitListener, NavigationView.OnNavigationItemSelectedListener {
    val TAG = "Levels"

    companion object{
        const val MY_PROFILE_REQUEST_CODE : Int = 11
        const val MY_HISTORY_REQUEST_CODE : Int = 12

    }

    private var isBackground = true

    private var tts: TextToSpeech? = null //Variable for Text to Speech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Utils.onActivityCreateSetTheme(this)

        setContentView(R.layout.activity_main)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        setupActionBar()

        nav_view.setNavigationItemSelectedListener(this)

        FirestoreClass().loadUserData(this)

        //Initialize the Text to Speech
        tts = TextToSpeech(this, this)



        btnSpeak1.setOnClickListener { view ->
            speakOut1(R.string.sound_level_1.toString())
        }

        btnSpeak2.setOnClickListener { view ->
            speakOut2(R.string.sound_level_2.toString())
        }

        btnSpeak3.setOnClickListener { view ->
            speakOut3(R.string.sound_level_3.toString())
        }


        btn_level_1.setOnClickListener {
            Utils.changeToTheme(this, Utils.THEME_LEVEL1)
            isBackground = false
           // if (getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE).getBoolean("LEVEL1", true)){
            //}
            val intent = Intent(this, CoursesActivity::class.java)
            startActivity(intent)
            finish()
            //startActivity(Intent(this@MainActivity, CoursesActivity::class.java))
            //finish()
        }

        btn_level_2.setOnClickListener {
            Utils.changeToTheme(this, Utils.THEME_LEVEL2)
            isBackground = false
            //if (getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE).getBoolean("LEVEL2", true)){
            //}
            val intent = Intent(this, CoursesActivity::class.java)
            startActivity(intent)
            finish()
            //startActivity(Intent(this@MainActivity, CoursesActivity::class.java))
            //finish()

        }

        btn_level_3.setOnClickListener {
            Utils.changeToTheme(this, Utils.THEME_LEVEL3)
            isBackground = false
           // if (getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE).getBoolean("LEVEL3", true)){
           // }
            val intent = Intent(this, CoursesActivity::class.java)
            startActivity(intent)
            finish()
            //startActivity(Intent(this@MainActivity, CoursesActivity::class.java))
            //finish()
        }


    }

    private fun speakOut1(text1: String) {
        val text1 = getString(R.string.sound_level_1)
        tts!!.speak(text1, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    private fun speakOut2(text2: String) {
        val text2 = getString(R.string.sound_level_2)
        tts!!.speak(text2, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    private fun speakOut3(text3: String) {
        val text3 = getString(R.string.sound_level_3)
        tts!!.speak(text3, TextToSpeech.QUEUE_FLUSH, null, "")
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
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
        if (getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE).getBoolean("MUSIC", true)) {
            startService(Intent(this, CoursesActivity::class.java))
        }
        isBackground = true
    }
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
        if (isBackground) {
            startService(Intent(this, CoursesActivity::class.java).putExtra("pause", true))
        }


    }
    override fun onRestart() {
        super.onRestart()
        if (getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE).getBoolean("MUSIC", true)) {
            startService(Intent(this, CoursesActivity::class.java))
        }
        isBackground = true
    }
    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }
    override fun onDestroy() {

        if(tts != null){
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()

    }
    override fun onBackPressed() {
        if(drawer_layout.isDrawerOpen(GravityCompat.START)){
            drawer_layout.closeDrawer(GravityCompat.START)
        }else{
            doubleBackToExit()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK
                && requestCode == MY_PROFILE_REQUEST_CODE){
                FirestoreClass().loadUserData(this)

        }else{
            Log.e("Cancelled", "Cancelled")
        }
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when(menuItem.itemId){
            R.id.nav_my_profile -> {

                startActivityForResult(
                        Intent(this@MainActivity,
                            MyProfileActivity::class.java),
                        MY_PROFILE_REQUEST_CODE)
            }

            R.id.nav_sign_out -> {
                //Sign out the user from firebase.
                FirebaseAuth.getInstance().signOut()

                val intent = Intent(this, IntroActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
            }

            R.id.nav_history -> {
                startActivity(
                        Intent(this,
                                HistoryActivity::class.java))

            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun setupActionBar() {
        setSupportActionBar(toolbar_main_activity)
        toolbar_main_activity.setNavigationIcon(R.drawable.ic_action_navigation_menu)

        toolbar_main_activity.setNavigationOnClickListener {
            //Toggle drawer
            toggleDrawer()
        }
    }

    private fun toggleDrawer(){
        if(drawer_layout.isDrawerOpen(GravityCompat.START)){
            drawer_layout.closeDrawer(GravityCompat.START)
        }else{
            drawer_layout.openDrawer(GravityCompat.START)
        }
    }

    /**
     * A function to get the current user details from firebase.
     */
    fun updateNavigationUserDetails(user: User) {

        // The instance of the header view of the navigation view.
        val headerView = nav_view.getHeaderView(0)

        // The instance of the user image of the navigation view.
        val navUserImage = headerView.findViewById<ImageView>(R.id.iv_user_image)

        // Load the user image in the ImageView.
        Glide
                .with(this@MainActivity)
                .load(user.image) // URL of the image
                .centerCrop() // Scale type of the image.
                .placeholder(R.drawable.ic_user_place_holder) // A default place holder
                .into(iv_user_image) // the view in which the image will be loaded.

        // The instance of the user name TextView of the navigation view.
        val navUsername = headerView.findViewById<TextView>(R.id.tv_username)
        // Set the user name
        navUsername.text = user.name


    }
}