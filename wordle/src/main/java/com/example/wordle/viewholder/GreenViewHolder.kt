package com.example.wordle.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wordle.R
import com.example.wordle.databinding.ItemGreenBinding

class GreenViewHolder (parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_green, parent, false)
) {
    private val binding = ItemGreenBinding.bind(itemView)

    fun bind(
        item: String,
    ) {
        binding.green.text = item.toUpperCase()
    }
}