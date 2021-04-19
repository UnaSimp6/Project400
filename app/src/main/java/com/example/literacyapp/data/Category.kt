package com.example.literacyapp.data

class Category {

    private var id: Int = 0
    private var name: String? = null

    constructor() {
        this.name = name
    }

    fun getId(): Int {
        return id
    }

    fun setId(id: Int) {
        this.id = id
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }


    companion object {
        const val SMALL_IN_BIG_WORDS = 1
        const val LINKING_WORDS = 2
        const val SAME_SOUNDING_WORDS = 3
        const val COMMON_ABBR = 4
    }
}