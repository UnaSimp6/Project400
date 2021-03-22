package com.example.literacyapp.data

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class ReadingDataSource(resources: Resources) {
    private val initialReadingList = readingList(resources)
    private val readingLiveData = MutableLiveData(initialReadingList)

    fun getReadingID(id: Long): Reading_Item? {
        readingLiveData.value?.let { reading ->
            return reading.firstOrNull{ it.id == id}
        }
        return null
    }

    fun getReadingList(): LiveData<List<Reading_Item>> {
        return readingLiveData
    }

    companion object {
    private var INSTANCE: ReadingDataSource? = null

    fun getDataSource(resources: Resources): ReadingDataSource{
        return synchronized(ReadingDataSource::class) {
            val newInstance = INSTANCE ?: ReadingDataSource(resources)
            INSTANCE = newInstance
            newInstance
        }
    }}
}