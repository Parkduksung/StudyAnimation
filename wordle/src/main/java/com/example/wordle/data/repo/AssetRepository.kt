package com.example.wordle.data.repo

interface AssetRepository {
    fun getWordList(): List<String>
    fun getRandomWord(): String
}