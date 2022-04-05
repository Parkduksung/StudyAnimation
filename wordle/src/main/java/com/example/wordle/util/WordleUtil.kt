package com.example.wordle.util

import android.content.Context
import java.io.IOException
import kotlin.random.Random

object WordleUtil {

    private const val ASSET_FILE_NAME = "wordlist.txt"

    fun getWordList(context: Context): List<String> {
        return try {
            val inputStream = context.assets.open("wordlist.txt")
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer).toString()
            String(buffer).split(",").map { it.trim().substring(1, 6) }
        } catch (e: IOException) {
            e.printStackTrace()
            emptyList()
        }
    }

    fun getRandomWord(context: Context): String {
        return try {
            val list = getWordList(context)
            val randomNum = Random.nextInt(list.size)
            list[randomNum]
        } catch (e: IOException) {
            e.printStackTrace()
            ""
        }

    }
}