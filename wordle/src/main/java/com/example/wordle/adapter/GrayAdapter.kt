package com.example.wordle.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wordle.viewholder.GrayViewHolder

class GrayAdapter : RecyclerView.Adapter<GrayViewHolder>() {

    private val grayList = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GrayViewHolder =
        GrayViewHolder(parent)

    override fun onBindViewHolder(holder: GrayViewHolder, position: Int) {
        holder.bind(grayList[position])
    }

    override fun getItemCount(): Int =
        grayList.size

    fun add(item: String, callback: (isAdd: Boolean) -> Unit) {
        if (!grayList.contains(item)) {
            grayList.add(item)
            notifyDataSetChanged()
        }
        callback(!grayList.contains(item))
    }
}