package com.example.studyanimation.animation.sharedTransition.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.studyanimation.R
import com.example.studyanimation.animation.sharedTransition.adapter.SharedTransitionItemClickListener

class SharedTransitionViewHolder(parent: ViewGroup, @LayoutRes private val layoutId: Int) :
    RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
    ) {

    private val image: ImageView = itemView.findViewById(R.id.image)

    fun bind(item: String, sharedTransitionItemClickListener: SharedTransitionItemClickListener) {

        Glide.with(itemView).load(item).into(image)

        image.apply {
            transitionName = item
            setOnClickListener {
                sharedTransitionItemClickListener.onClick(item, it)
            }
        }
    }

}