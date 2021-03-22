/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.example.literacyapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.literacyapp.R


/* A list always displaying the lessons. */


class ReadingHeaderAdapter: RecyclerView.Adapter<ReadingHeaderAdapter.HeaderViewHolder>() {
    private var readingCount: Int = 0

/* ViewHolder for displaying header. */

    class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val readingNumberTextView: TextView = itemView.findViewById(R.id.reading_number_text)

        fun bind(readingCount: Int) {
            readingNumberTextView.text = readingCount.toString()
        }
    }


/* Inflates view and returns HeaderViewHolder. */

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.header_reading_item, parent, false)
        return HeaderViewHolder(view)
    }


/* Binds number of lessons to the header. */

    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {
        holder.bind(readingCount)
    }


/* Returns number of items, since there is only one item in the header return one  */

    override fun getItemCount(): Int {
        return 1
    }


/* Updates header to display number of lessons if a lesson is added or removed (for future use case). */

    fun updateReadingCount(updatedReadingCount: Int) {
        readingCount = updatedReadingCount
        notifyDataSetChanged()
    }
}
