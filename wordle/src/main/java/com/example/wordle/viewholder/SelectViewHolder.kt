package com.example.wordle.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wordle.Color
import com.example.wordle.convertBackgroundAndTextColor
import com.example.wordle.R
import com.example.wordle.databinding.ItemSelectBinding

class SelectViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_select, parent, false)
) {
    private val binding = ItemSelectBinding.bind(itemView)

    fun bind(
        item: List<Pair<Color, String>>,
    ) {
        item.forEachIndexed { index, pair ->
            when (index) {
                0 -> {
                    with(binding.input1) {
                        text = pair.second.toUpperCase()
                        this.convertBackgroundAndTextColor(pair.first)
                    }
                }
                1 -> {
                    with(binding.input2) {
                        text = pair.second.toUpperCase()
                        this.convertBackgroundAndTextColor(pair.first)
                    }
                }

                2 -> {
                    with(binding.input3) {
                        text = pair.second.toUpperCase()
                        this.convertBackgroundAndTextColor(pair.first)
                    }
                }
                3 -> {
                    with(binding.input4) {
                        text = pair.second.toUpperCase()
                        this.convertBackgroundAndTextColor(pair.first)
                    }
                }
                4 -> {
                    with(binding.input5) {
                        text = pair.second.toUpperCase()
                        this.convertBackgroundAndTextColor(pair.first)
                    }
                }
            }
        }
    }
}