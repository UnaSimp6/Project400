package com.example.literacyapp.utils

object Constants {

    const val USERS: String = "users"

    const val IMAGE: String = "image"
    const val NAME: String = "name"
    const val MOBILE: String = "mobile"
    const val READ_STORAGE_PERMISSION_CODE =1
    const val PICK_IMAGE_REQUEST_CODE = 2
    const val ReadingWSCount = 4

    // TODO (STEP 3: Add the SharedPreferences name and key name for storing the response data in it.)
    // START
    const val PREFERENCE_NAME = "QuizAppPreference"
    const val WORKSHEET_RESPONSE_DATA = "quiz_response_data"
    const val LESSONS_RESPONSE_DATA = "lessons_response_data"
    const val LESSONCOMPLETED = 0
    const val COURSES_RESPONSE_DATA = "courses_response_data"
    const val COURSESCOMPLETED = 0
    // END
}