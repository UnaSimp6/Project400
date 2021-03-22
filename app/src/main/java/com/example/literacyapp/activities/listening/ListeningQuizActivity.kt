package com.example.literacyapp.activities.listening

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.literacyapp.R
import com.example.literacyapp.utils.ListeningQuestions
import kotlinx.android.synthetic.main.activity_courses.*
import kotlinx.android.synthetic.main.activity_listening_quiz.*
import android.media.MediaPlayer
import com.example.literacyapp.activities.BaseActivity
import com.example.literacyapp.model.ListeningQuiz
import kotlinx.android.synthetic.main.activity_results.*

class ListeningQuizActivity : BaseActivity(), View.OnClickListener {

            var mMediaPlayer: MediaPlayer? = null

            // START
            private var mCurrentPosition: Int = 1 // Default and the first question position
            private var mQuestionsList: ArrayList<ListeningQuiz>? = null
            private var mCorrectAnswers: Int = 0
            private var mSelectedOptionPosition: Int = 0
            private var mUserName: String? = null
            // END

            /**
             * This function is auto created by Android when the Activity Class is created.
             */
            override fun onCreate(savedInstanceState: Bundle?) {
                //This call the parent constructor
                super.onCreate(savedInstanceState)
                // This is used to align the xml view to this class
                setContentView(R.layout.activity_listening_quiz)

                // This is used to hide the status bar and make the splash screen as a full screen activity.
                window.setFlags(
                        WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN
                )


                mUserName = intent.getStringExtra(ListeningQuestions.USER_NAME)


                // START
                mQuestionsList = ListeningQuestions.getQuestions()
                // END

                setQuestion()

                // START
                tv_option_one.setOnClickListener(this)
                tv_option_two.setOnClickListener(this)
                tv_option_three.setOnClickListener(this)
                tv_option_four.setOnClickListener(this)
                btn_submit.setOnClickListener(this)
                // END
            }

    // 1. Plays the audio file
    fun playSound(view: View) {
        if (mMediaPlayer == null) {
            mMediaPlayer = MediaPlayer.create(this, R.raw.train)
            mMediaPlayer!!.isLooping = false
            mMediaPlayer!!.start()
        } else mMediaPlayer!!.start()
    }

    // 2. Pause playback
    fun pauseSound(view: View) {
        if (mMediaPlayer != null && mMediaPlayer!!.isPlaying) mMediaPlayer!!.pause()
    }

    // 3. {optional} Stops playback
    fun stopSound(view: View) {
        if (mMediaPlayer != null) {
            mMediaPlayer!!.stop()
            mMediaPlayer!!.release()
            mMediaPlayer = null
        }
    }

    // 4. Closes the MediaPlayer when the app is closed
    override fun onStop() {
        super.onStop()
        if (mMediaPlayer != null) {
            mMediaPlayer!!.release()
            mMediaPlayer = null
        }
    }

            override fun onClick(v: View?) {

                when (v?.id) {

                    R.id.tv_option_one -> {

                        selectedOptionView(tv_option_one, 1)
                    }

                    R.id.tv_option_two -> {

                        selectedOptionView(tv_option_two, 2)
                    }

                    R.id.tv_option_three -> {

                        selectedOptionView(tv_option_three, 3)
                    }

                    R.id.tv_option_four -> {

                        selectedOptionView(tv_option_four, 4)
                    }

                    R.id.btn_submit -> {
                        if(mSelectedOptionPosition == 0){
                            mCurrentPosition ++

                            when{
                                mCurrentPosition <= mQuestionsList!!.size ->{
                                    setQuestion()
                                }else ->{
                                val intent = Intent(this, ListeningResultsActivity::class.java)
                                //results()
                                intent.putExtra(ListeningQuestions.USER_NAME, mUserName)
                                intent.putExtra(ListeningQuestions.CORRECT_LISTENING_ANSWERS, mCorrectAnswers)
                                intent.putExtra(ListeningQuestions.TOTAL_LISTENING_QUESTIONS, mQuestionsList!!.size)
                                startActivity(intent)
                                finish()

                            }
                            }
                        }else{
                            val question = mQuestionsList?.get(mCurrentPosition -1)
                            if(question!!.correctAnswer != mSelectedOptionPosition){
                                answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                            }else{
                                mCorrectAnswers++
                            }
                            answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

                            if(mCurrentPosition == mQuestionsList!!.size){
                                btn_submit.text = "Finish"
                            }else{
                                btn_submit.text = "GO TO NEXT QUESTION"
                            }
                            mSelectedOptionPosition = 0
                        }

                    }
                }
            }

            // TODO (STEP 1 : Lets create a function to set the question in the UI components which we have done earlier the onCreate method. And make some of the variables global which we will be using later.)
            // START
            /**
             * A function for setting the question to UI components.
             */
            private fun setQuestion() {

                val question =
                    mQuestionsList!!.get(mCurrentPosition - 1) // Getting the question from the list with the help of current position.

                defaultOptionsView()

                if(mCurrentPosition == mQuestionsList!!.size){
                    btn_submit.text = "FINISH"
                }else{
                    btn_submit.text = "SUBMIT"
                }

                progressBar.progress = mCurrentPosition
                tv_progress.text = "$mCurrentPosition" + "/" + progressBar.getMax()

                tv_question.text = question.question
                tv_option_one.text = question.optionOne
                tv_option_two.text = question.optionTwo
                tv_option_three.text = question.optionThree
                tv_option_four.text = question.optionFour
            }
            // END

            // TODO (STEP 6: Create a function for view for highlighting the selected option.)
            // START

            private fun answerView(answer: Int, drawablesView: Int){
                when(answer){
                    1 ->{
                        tv_option_one.background = ContextCompat.getDrawable(
                            this, drawablesView
                        )
                    }
                    2 ->{
                        tv_option_two.background = ContextCompat.getDrawable(
                            this, drawablesView
                        )
                    }
                    3 ->{
                        tv_option_three.background = ContextCompat.getDrawable(
                            this, drawablesView
                        )
                    }
                    4 ->{
                        tv_option_four.background = ContextCompat.getDrawable(
                            this, drawablesView
                        )
                    }
                }
            }

            private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {

                defaultOptionsView()

                mSelectedOptionPosition = selectedOptionNum

                tv.setTextColor(
                    Color.parseColor("#363A43")
                )
                tv.setTypeface(tv.typeface, Typeface.BOLD)
                tv.background = ContextCompat.getDrawable(
                    this@ListeningQuizActivity,
                    R.drawable.selected_option_border_bg
                )
            }

            // TODO (STEP 8: Create a function to set default options view.)
            // START
            /**
             * A function to set default options view when the new question is loaded or when the answer is reselected.
             */
            private fun defaultOptionsView() {

                val options = ArrayList<TextView>()
                options.add(0, tv_option_one)
                options.add(1, tv_option_two)
                options.add(2, tv_option_three)
                options.add(3, tv_option_four)

                for (option in options) {
                    option.setTextColor(Color.parseColor("#7A8089"))
                    option.typeface = Typeface.DEFAULT
                    option.background = ContextCompat.getDrawable(
                        this@ListeningQuizActivity,
                        R.drawable.default_option_border_bg
                    )
                }
            }


        }
