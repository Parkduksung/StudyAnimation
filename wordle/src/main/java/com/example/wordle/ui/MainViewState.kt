package com.example.wordle.ui

import com.example.wordle.constant.Color

sealed class MainViewState {
    data class Error(val message: String) : MainViewState()
    data class Yield(val list: List<Pair<Color, String>>) : MainViewState()
}
