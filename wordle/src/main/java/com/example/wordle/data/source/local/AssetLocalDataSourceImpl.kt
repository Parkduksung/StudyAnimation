package com.example.wordle.data.source.local

import android.content.Context
import com.example.wordle.util.WordleUtil

class AssetLocalDataSourceImpl(private val context: Context) : AssetLocalDataSource {

    override fun getWordList(): List<String> =
        WordleUtil.getWordList(context)

    override fun getRandomWord(): String =
        WordleUtil.getRandomWord(context)


}
