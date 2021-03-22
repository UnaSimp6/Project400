package com.example.literacyapp.activities.writing

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.literacyapp.R
import com.example.literacyapp.activities.BaseActivity
import com.example.literacyapp.model.WritingQuiz
import com.example.literacyapp.utils.WritingQuestions
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_results.*
import kotlinx.android.synthetic.main.activity_writing_quiz.*

class WritingQuizActivity : BaseActivity(), View.OnClickListener {



            // START
            private var mCurrentPosition: Int = 1 // Default and the first question position
            private var mQuestionsList: ArrayList<WritingQuiz>? = null
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
                setContentView(R.layout.activity_writing_quiz)

                // This is used to hide the status bar and make the splash screen as a full screen activity.
                window.setFlags(
                        WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN
                )


                mUserName = intent.getStringExtra(WritingQuestions.USER_WRITING_NAME)


                // START
                mQuestionsList = WritingQuestions.getQuestions()
                // END

                setQuestion()

                // START
                tv_writing_option_one.setOnClickListener(this)
                tv_writing_option_two.setOnClickListener(this)
                tv_writing_option_three.setOnClickListener(this)
                tv_writing_option_four.setOnClickListener(this)
                btn_submit.setOnClickListener(this)
                // END
            }


            override fun onClick(v: View?) {

                when (v?.id) {

                    R.id.tv_writing_option_one -> {

                        selectedOptionView(tv_writing_option_one, 1)
                    }

                    R.id.tv_writing_option_two -> {

                        selectedOptionView(tv_writing_option_two, 2)
                    }

                    R.id.tv_writing_option_three -> {

                        selectedOptionView(tv_writing_option_three, 3)
                    }

                    R.id.tv_writing_option_four -> {

                        selectedOptionView(tv_writing_option_four, 4)
                    }

                    R.id.btn_submit -> {
                        if(mSelectedOptionPosition == 0){
                            mCurrentPosition ++

                            when{
                                mCurrentPosition <= mQuestionsList!!.size ->{
                                    setQuestion()
                                }else ->{
                                val intent = Intent(this, WritingResultsActivity::class.java)
                                intent.putExtra(WritingQuestions.USER_WRITING_NAME, mUserName)
                                intent.putExtra(WritingQuestions.CORRECT_WRITING_ANSWERS, mCorrectAnswers)
                                intent.putExtra(WritingQuestions.TOTAL_WRITING_QUESTIONS, mQuestionsList!!.size)
                                startActivity(intent)
                                finish()

                            }
                            }
                        }else{
                            val question = mQuestionsList?.get(mCurrentPosition -1)
                            if(question!!.writingcorrectAnswer != mSelectedOptionPosition){
                                answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                            }else{
                                mCorrectAnswers++
                            }
                            answerView(question.writingcorrectAnswer, R.drawable.correct_option_border_bg)

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

                tv_writing_question.text = question.writingquestion
                tv_writing_option_one.text = question.writingoptionOne
                tv_writing_option_two.text = question.writingoptionTwo
                tv_writing_option_three.text = question.writingoptionThree
                tv_writing_option_four.text = question.writingoptionFour
            }
            // END

            // TODO (STEP 6: Create a function for view for highlighting the selected option.)
            // START

            private fun answerView(answer: Int, drawablesView: Int){
                when(answer){
                    1 ->{
                        tv_writing_option_one.background = ContextCompat.getDrawable(
                            this, drawablesView
                        )
                    }
                    2 ->{
                        tv_writing_option_two.background = ContextCompat.getDrawable(
                            this, drawablesView
                        )
                    }
                    3 ->{
                        tv_writing_option_three.background = ContextCompat.getDrawable(
                            this, drawablesView
                        )
                    }
                    4 ->{
                        tv_writing_option_four.background = ContextCompat.getDrawable(
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
                    this@WritingQuizActivity,
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
                options.add(0, tv_writing_option_one)
                options.add(1, tv_writing_option_two)
                options.add(2, tv_writing_option_three)
                options.add(3, tv_writing_option_four)

                for (option in options) {
                    option.setTextColor(Color.parseColor("#7A8089"))
                    option.typeface = Typeface.DEFAULT
                    option.background = ContextCompat.getDrawable(
                        this@WritingQuizActivity,
                        R.drawable.default_option_border_bg
                    )
                }
            }


        }
