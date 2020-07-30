package com.example.studyanimation.animation.sharedTransition

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.studyanimation.BaseActivity
import com.example.studyanimation.R
import com.example.studyanimation.animation.sharedTransition.adapter.SharedTransitionAdapter
import com.example.studyanimation.animation.sharedTransition.adapter.SharedTransitionItemClickListener
import kotlinx.android.synthetic.main.activity_shared_transition.*

class SharedTransitionActivity : BaseActivity(R.layout.activity_shared_transition),
    SharedTransitionItemClickListener {

    override fun onClick(item: String, imageView: View) {
        goToDetails(item,imageView)
    }

    private val sharedAdapter by lazy { SharedTransitionAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedAdapter.setSharedTransitionItemClickListener(this)

        rv_sharedTransition.run {
            adapter = sharedAdapter
            layoutManager = GridLayoutManager(context, 4)
        }

        val list = mutableListOf<String>()
        for (i in 169..216) {
            list.add("https://picsum.photos/1000/700?image=$i")
        }

        sharedAdapter.addAll(list)

    }

    fun goToDetails(url: String, imageView: View) {
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this,
            imageView,
            imageView.transitionName
        ).toBundle()
        Intent(this, SharedTransitionDetailsActivity::class.java)
            .putExtra(IMAGE_URL_KEY, url)
            .let {
                startActivity(it, options)
            }
    }

    companion object {
        const val IMAGE_URL_KEY = "url"
    }

}