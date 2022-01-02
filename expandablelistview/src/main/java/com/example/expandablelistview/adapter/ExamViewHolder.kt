package com.example.expandablelistview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.expandablelistview.R
import com.example.expandablelistview.databinding.ItemExamBinding

class ExamViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_exam, parent, false)
) {
    private val binding = ItemExamBinding.bind(itemView)

    private val detailAdapter = DetailAdapter()

    fun bind(item: Exam, listener: (item: Exam) -> Unit) {
        with(binding) {
            no.text = item.no.toString()
            name.text = item.name
            memo.text = item.memo
            expand.text = item.isExpand.toString()
            binding.containerExpand.isVisible = item.isExpand

            binding.rvNest.isVisible = item.detailList.isNotEmpty()
            binding.rvNest.run {
                adapter = detailAdapter
            }
            binding.rvNest.setHasFixedSize(true)
            binding.rvNest.isNestedScrollingEnabled = true

            detailAdapter.addAll(item.detailList)


            if (binding.containerExpand.isVisible) {
                binding.arrow.setImageResource(R.drawable.ic_arrow_up)
                binding.containerExpand.animate().setDuration(200L).rotation(360f)
            } else {
                binding.arrow.setImageResource(R.drawable.ic_arrow_down)
                binding.containerExpand.animate().setDuration(200L).rotation(0f)
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
    val detailList: List<DetailItem> = emptyList()
)

data class DetailItem(
    val type: String,
    val detail: String
)
