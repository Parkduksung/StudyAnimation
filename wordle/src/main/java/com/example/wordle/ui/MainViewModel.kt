package com.example.wordle.ui

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wordle.constant.Color
import com.example.wordle.data.repo.AssetRepository

class MainViewModel(
    private val assetRepository: AssetRepository
) : ViewModel() {

    private val randomStringObservableField = ObservableField<String>()
    val inputStringLiveData = MutableLiveData<String>()

    private val _mainViewStateLiveData = MutableLiveData<MainViewState>()
    val mainViewStateLiveData: LiveData<MainViewState> = _mainViewStateLiveData

    init {
        getRandomString()
    }

    private fun getRandomString() {
        randomStringObservableField.set(assetRepository.getRandomWord())
    }

    fun summit() {
        inputStringLiveData.value?.let { inputString ->
            if (inputString.length == WORD_LENGTH) {
                if (assetRepository.getWordList().contains(inputString)) {
                    onViewStateChanged(
                        MainViewState.Yield(
                            convertList(
                                randomStringObservableField.get()!!,
                                inputString
                            )
                        )
                    )
                } else {
                    onViewStateChanged(MainViewState.Error("Not In Dictionary"))
                }
            } else {
                onViewStateChanged(MainViewState.Error("Not 5 Letter"))
            }
        } ?: onViewStateChanged(MainViewState.Error("Not input Letter"))
    }


    private fun convertList(result: String, input: String): List<Pair<Color, String>> {
        val toInputCharList = input.mapIndexed { index, c -> Pair(index, c) }

        val convertList = mutableListOf<Pair<Color, String>>()

        toInputCharList.forEach { inputItem ->
            if (result[inputItem.first] == inputItem.second) {
                convertList.add(Pair(Color.Green, inputItem.second.toString()))
            } else {
                if (result.contains(inputItem.second)) {
                    convertList.add(Pair(Color.Yellow, inputItem.second.toString()))
                } else {
                    convertList.add(Pair(Color.Gray, inputItem.second.toString()))
                }
            }
        }
        return convertList
    }

    private fun onViewStateChanged(viewState: MainViewState) {
        _mainViewStateLiveData.value = viewState
        _mainViewStateLiveData.value = null
    }

    companion object {
        private const val WORD_LENGTH = 5
    }

}