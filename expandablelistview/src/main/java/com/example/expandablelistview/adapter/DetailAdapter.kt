package com.example.expandablelistview.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class DetailAdapter : RecyclerView.Adapter<DetailViewHolder>() {

    private val detailItemList = mutableListOf<DetailItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder =
        DetailViewHolder(parent)

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        holder.bind(detailItemList[position])
    }

    override fun getItemCount(): Int =
        detailItemList.size

    fun addAll(list: List<DetailItem>) {
        detailItemList.addAll(list)
        notifyDataSetChanged()
    }
}