package com.example.wordle.data.source.local

import android.annotation.SuppressLint
import android.content.Context
import com.example.wordle.util.WordleUtil

class AssetLocalDataSourceImpl(private val context: Context) : AssetLocalDataSource {

    override fun getWordList(): List<String> =
        WordleUtil.getWordList(context)

    override fun getRandomWord(): String =
        WordleUtil.getRandomWord(context)

    companion object {

        @SuppressLint("StaticFieldLeak")
        private var instance: AssetLocalDataSourceImpl? = null

        fun getInstance(context: Context): AssetLocalDataSource =
            instance ?: AssetLocalDataSourceImpl(context).also {
                instance = it
            }

    }

}
