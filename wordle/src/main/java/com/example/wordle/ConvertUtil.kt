package com.example.wordle

import android.widget.TextView

object ConvertUtil {
    fun convertList(result: String, input: String): List<Pair<Color, String>> {
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
}

sealed class Color {
    object Yellow : Color()
    object Green : Color()
    object Gray : Color()
}

fun TextView.convertBackgroundAndTextColor(color: Color) {
    when (color) {
        is Color.Yellow -> {
            setBackgroundColor(android.graphics.Color.parseColor("#FFFFE46F"))
            setTextColor(android.graphics.Color.parseColor("#FFFFFFFF"))
        }
        is Color.Green -> {
            setBackgroundColor(android.graphics.Color.parseColor("#FF99F691"))
            setTextColor(android.graphics.Color.parseColor("#FF000000"))
        }
        is Color.Gray -> {
            setBackgroundColor(android.graphics.Color.parseColor("#FF787C7E"))
            setTextColor(android.graphics.Color.parseColor("#FF000000"))
        }
    }
}