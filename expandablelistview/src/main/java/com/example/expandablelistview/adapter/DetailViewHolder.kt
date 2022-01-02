package com.example.expandablelistview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.expandablelistview.R
import com.example.expandablelistview.databinding.ItemDetailBinding

class DetailViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_detail, parent, false)
) {

    private val binding = ItemDetailBinding.bind(itemView)

    fun bind(item: DetailItem) {
        with(binding) {
            type.text = item.type
            detail.text = item.detail
        }
    }

}