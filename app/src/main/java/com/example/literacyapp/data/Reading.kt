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

package com.example.literacyapp.data

import android.content.res.Resources
import com.example.literacyapp.R

/* Returns initial list of flowers. */
fun readingList(resources: Resources): List<Reading_Item> {
    return listOf(
        Reading_Item(
            id = 1,
            name = resources.getString(R.string.reading_level_2_lessons1),
            image = R.drawable.ic_reading_words,
            subtitle = resources.getString(R.string.reading_completed_lesson)
        ),
        Reading_Item(
            id = 2,
            name = resources.getString(R.string.reading_level_2_lessons2),
            image = R.drawable.ic_using_rules,
            subtitle = resources.getString(R.string.reading_completed_lesson)
        ),
        Reading_Item(
            id = 3,
            name = resources.getString(R.string.reading_level_2_lessons3),
            image = R.drawable.ic_reading_texts,
            subtitle = resources.getString(R.string.reading_completed_lesson)
        ),
        Reading_Item(
            id = 4,
            name = resources.getString(R.string.reading_level_2_lessons4),
            image = R.drawable.ic_finding_info,
            subtitle = resources.getString(R.string.reading_completed_lesson)
        ),
        Reading_Item(
            id = 5,
            name = resources.getString(R.string.reading_level_2_lessons5),
            image = R.drawable.ic_reading_strategies,
            subtitle = resources.getString(R.string.reading_completed_lesson)
        )
    )
}

