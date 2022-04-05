package com.example.wordle.data.source.local

interface AssetLocalDataSource {
    fun getWordList(): List<String>
    fun getRandomWord(): String
}