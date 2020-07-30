package com.example.studyanimation.animation.sharedTransition.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.studyanimation.R
import com.example.studyanimation.animation.sharedTransition.adapter.viewholder.SharedTransitionViewHolder

class SharedTransitionAdapter : RecyclerView.Adapter<SharedTransitionViewHolder>() {

    private val imageList = mutableListOf<String>()

    private lateinit var sharedTransitionItemClickListener: SharedTransitionItemClickListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SharedTransitionViewHolder =
        SharedTransitionViewHolder(parent, R.layout.item_shared_transition)


    override fun getItemCount(): Int =
        imageList.size

    override fun onBindViewHolder(holder: SharedTransitionViewHolder, position: Int) {
        holder.bind(imageList[position], sharedTransitionItemClickListener)
    }

    fun addAll(list: List<String>) {
        imageList.addAll(list)
    }

    fun setSharedTransitionItemClickListener(listener: SharedTransitionItemClickListener) {
        sharedTransitionItemClickListener = listener
    }
}