package com.example.wordle.data.repo

import android.content.Context
import com.example.wordle.data.source.local.AssetLocalDataSource
import com.example.wordle.util.InjectUtil

class AssetRepositoryImpl(private val assetLocalDataSource: AssetLocalDataSource) :
    AssetRepository {
    override fun getWordList(): List<String> =
        assetLocalDataSource.getWordList()

    override fun getRandomWord(): String =
        assetLocalDataSource.getRandomWord()


    companion object {

        private var instance: AssetRepositoryImpl? = null

        fun getInstance(context: Context): AssetRepository =
            instance ?: AssetRepositoryImpl(InjectUtil.provideAssetLocalDataSource(context)).also {
                instance = it
            }

    }

}