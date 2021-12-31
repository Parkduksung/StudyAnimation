package com.example.expandablelistview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.expandablelistview.R
import com.example.expandablelistview.databinding.ItemExamBinding

class ExamViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_exam, parent, false)
) {
    private val binding = DataBindingUtil.bind<ItemExamBinding>(itemView)

    fun bind(item: Exam, listener: ExamItemClickListener) {

        binding?.let {
            with(it) {
                no.text = item.no.toString()
                name.text = item.name
                memo.text = item.memo
            }

            itemView.setOnClickListener {
                binding.containerExpand.isVisible = !binding.containerExpand.isVisible
                listener.onItemClick(item)
            }
        }
    }
}

data class Exam(
    val no: Int,
    val name: String,
    val memo: String
)


interface ExamItemClickListener {
    fun onItemClick(item: Exam)
}