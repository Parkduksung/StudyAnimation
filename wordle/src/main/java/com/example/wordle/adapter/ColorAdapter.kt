package com.example.wordle.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wordle.Color
import com.example.wordle.viewholder.ColorViewHolder

class ColorAdapter : RecyclerView.Adapter<ColorViewHolder>() {

    private val colorList = mutableListOf<Pair<Color, String>>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder =
        ColorViewHolder.create(parent)

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        holder.bind(colorList[position])
    }

    override fun getItemCount(): Int =
        colorList.size

    fun add(item: Pair<Color, String>, callback: (isAdd: Boolean) -> Unit) {
        callback(!colorList.contains(item))
        if (!colorList.contains(item)) {
            colorList.add(item)
            notifyDataSetChanged()
        }
    }
}