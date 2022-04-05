package com.example.wordle.data.repo

import com.example.wordle.data.source.local.AssetLocalDataSource

class AssetRepositoryImpl(private val assetLocalDataSource: AssetLocalDataSource) :
    AssetRepository {
    override fun getWordList(): List<String> =
        assetLocalDataSource.getWordList()

    override fun getRandomWord(): String =
        assetLocalDataSource.getRandomWord()
}