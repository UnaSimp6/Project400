package com.example.literacyapp.data

import android.content.Context
import androidx.annotation.DrawableRes
import com.example.literacyapp.R

data class Reading_Item(
        val id: Long,
        val name: String,
        //val arrow: Int,
        @DrawableRes
        val image: Int,
        val subtitle: String
)


