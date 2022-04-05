package com.example.wordle.ui.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wordle.R
import com.example.wordle.constant.Color
import com.example.wordle.databinding.ItemColorBinding

class ColorViewHolder(private val binding: ItemColorBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Pair<Color, String>) {
        binding.color = item.first
        binding.string = item.second.toUpperCase()
    }

    companion object {

        fun create(
            parent: ViewGroup
        ): ColorViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_color, parent, false)
            val binding = ItemColorBinding.bind(view)
            return ColorViewHolder(binding)
        }
    }
}