package com.example.wordle.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wordle.viewholder.GreenViewHolder

class GreenAdapter : RecyclerView.Adapter<GreenViewHolder>() {

    private val greenList = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GreenViewHolder =
        GreenViewHolder(parent)

    override fun onBindViewHolder(holder: GreenViewHolder, position: Int) {
        holder.bind(greenList[position])
    }

    override fun getItemCount(): Int =
        greenList.size

    fun add(item: String, callback: (isAdd: Boolean) -> Unit) {
        if (!greenList.contains(item)) {
            greenList.add(item)
            notifyDataSetChanged()
        }
        callback(!greenList.contains(item))
    }
}