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

    fun bind(item: Exam, listener: (item: Exam) -> Unit) {

        binding?.let {
            with(it) {
                no.text = item.no.toString()
                name.text = item.name
                memo.text = item.memo
                expand.text = item.isExpand.toString()
                binding.containerExpand.isVisible = item.isExpand
                if (binding.containerExpand.isVisible) {
                    binding.containerExpand.animate().setDuration(200L).rotation(360f)
                } else {
                    binding.containerExpand.animate().setDuration(200L).rotation(0f)
                }
            }
        }

        itemView.setOnClickListener {
            listener(item)
        }
    }
}

data class Exam(
    val no: Int,
    val name: String,
    val memo: String,
    val isExpand: Boolean = false,
)


interface ExamItemClickListener {
    fun onItemClick(item: Exam)
}