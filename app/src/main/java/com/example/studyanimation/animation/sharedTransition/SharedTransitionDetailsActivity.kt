package com.example.studyanimation.animation.sharedTransition

import android.os.Bundle
import com.example.studyanimation.BaseActivity
import com.example.studyanimation.R
import com.example.studyanimation.animation.sharedTransition.SharedTransitionActivity.Companion.IMAGE_URL_KEY
import com.example.studyanimation.ext.load
import kotlinx.android.synthetic.main.activity_shared_transition_details.*

class SharedTransitionDetailsActivity : BaseActivity(R.layout.activity_shared_transition_details) {

    private val url by lazy {
        intent.getStringExtra(IMAGE_URL_KEY)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportPostponeEnterTransition()

        iv_details.transitionName = url
        iv_details.load(url!!, true) {
            supportStartPostponedEnterTransition()
        }
    }
}