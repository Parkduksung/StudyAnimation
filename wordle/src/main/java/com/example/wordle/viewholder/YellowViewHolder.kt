package com.example.wordle.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wordle.R
import com.example.wordle.databinding.ItemYellowBinding

class YellowViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_yellow, parent, false)
) {
    private val binding = ItemYellowBinding.bind(itemView)

    fun bind(
        item: String,
    ) {
        binding.yellow.text = item.toUpperCase()
    }
}