package com.example.wordle.util

import android.content.Context
import com.example.wordle.data.repo.AssetRepository
import com.example.wordle.data.repo.AssetRepositoryImpl
import com.example.wordle.data.source.local.AssetLocalDataSource
import com.example.wordle.data.source.local.AssetLocalDataSourceImpl

object InjectUtil {

    fun provideAssetRepository(context: Context): AssetRepository =
        AssetRepositoryImpl.getInstance(context)

    fun provideAssetLocalDataSource(context: Context): AssetLocalDataSource =
        AssetLocalDataSourceImpl.getInstance(context)
}