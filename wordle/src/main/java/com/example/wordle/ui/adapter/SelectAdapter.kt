package com.example.wordle.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wordle.constant.Color
import com.example.wordle.ui.adapter.viewholder.SelectViewHolder

class SelectAdapter : RecyclerView.Adapter<SelectViewHolder>() {

    private val wordList = mutableListOf<List<Pair<Color, String>>>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectViewHolder =
        SelectViewHolder.create(parent)

    override fun onBindViewHolder(holder: SelectViewHolder, position: Int) {
        holder.bind(wordList[position])
    }

    override fun getItemCount(): Int =
        wordList.size

    fun add(item: List<Pair<Color, String>>, callback: (isAdd: Boolean) -> Unit) {
        callback(!wordList.contains(item))
        if (!wordList.contains(item)) {
            wordList.add(item)
            notifyDataSetChanged()

        }
    }
}