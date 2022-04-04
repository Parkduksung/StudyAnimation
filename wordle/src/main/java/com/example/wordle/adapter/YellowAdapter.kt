package com.example.wordle.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wordle.viewholder.YellowViewHolder

class YellowAdapter : RecyclerView.Adapter<YellowViewHolder>() {

    private val yellowList = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YellowViewHolder =
        YellowViewHolder(parent)

    override fun onBindViewHolder(holder: YellowViewHolder, position: Int) {
        holder.bind(yellowList[position])
    }

    override fun getItemCount(): Int =
        yellowList.size

    fun add(item: String, callback: (isAdd: Boolean) -> Unit) {
        if (!yellowList.contains(item)) {
            yellowList.add(item)
            notifyDataSetChanged()
        }
        callback(!yellowList.contains(item))
    }
}