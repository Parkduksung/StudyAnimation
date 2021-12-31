package com.example.expandablelistview.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ExamAdapter : RecyclerView.Adapter<ExamViewHolder>() {

    private val itemList = mutableListOf<Exam>()

    private lateinit var examItemClickListener: ExamItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExamViewHolder =
        ExamViewHolder(parent)

    override fun onBindViewHolder(holder: ExamViewHolder, position: Int) {
        holder.bind(itemList[position], examItemClickListener)
    }

    override fun getItemCount(): Int =
        itemList.size

    fun addAll(list: List<Exam>) {
        itemList.addAll(list)
        notifyDataSetChanged()
    }

    fun setOnClickListener(listener: ExamItemClickListener) {
        examItemClickListener = listener
    }
}