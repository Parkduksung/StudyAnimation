package com.example.wordle.ui.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wordle.convertBackgroundAndTextColor
import com.example.wordle.R
import com.example.wordle.constant.Color
import com.example.wordle.databinding.ItemSelectBinding

class SelectViewHolder(private val binding: ItemSelectBinding) :
    RecyclerView.ViewHolder(binding.root) {

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

    companion object {
        fun create(
            parent: ViewGroup
        ): SelectViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_select, parent, false)
            val binding = ItemSelectBinding.bind(view)
            return SelectViewHolder(binding)
        }


    }
}