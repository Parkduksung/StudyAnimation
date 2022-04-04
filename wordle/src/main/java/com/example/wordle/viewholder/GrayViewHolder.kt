package com.example.wordle.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wordle.R
import com.example.wordle.databinding.ItemGrayBinding

class GrayViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_gray, parent, false)
) {
    private val binding = ItemGrayBinding.bind(itemView)

    fun bind(
        item: String,
    ) {
        binding.gray.text = item.toUpperCase()
    }
}