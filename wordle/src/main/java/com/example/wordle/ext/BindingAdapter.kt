package com.example.wordle.ext

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.wordle.Color


@BindingAdapter("convertBackgroundAndTextColor")
fun convertBackgroundAndTextColor(view: TextView?, color: Color) {
    when (color) {
        is Color.Yellow -> {
            view?.setBackgroundColor(android.graphics.Color.parseColor("#FFFFE46F"))
            view?.setTextColor(android.graphics.Color.parseColor("#FFFFFFFF"))
        }
        is Color.Green -> {
            view?.setBackgroundColor(android.graphics.Color.parseColor("#FF99F691"))
            view?.setTextColor(android.graphics.Color.parseColor("#FF000000"))
        }
        is Color.Gray -> {
            view?.setBackgroundColor(android.graphics.Color.parseColor("#FF787C7E"))
            view?.setTextColor(android.graphics.Color.parseColor("#FF000000"))
        }
    }
}