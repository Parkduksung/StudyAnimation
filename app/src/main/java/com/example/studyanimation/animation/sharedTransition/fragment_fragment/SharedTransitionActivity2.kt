package com.example.studyanimation.animation.sharedTransition.fragment_fragment

import android.os.Bundle
import com.example.studyanimation.BaseActivity
import com.example.studyanimation.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*

class SharedTransitionActivity2 : BaseActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction().add(R.id.main_container, HomeFragment()).commit()

        btn_transition.setOnClickListener {

            supportFragmentManager.beginTransaction().addToBackStack(null)
                .replace(R.id.main_container, DetailsFragment())
                .addSharedElement(imageView1, imageView1.transitionName).commit()

        }
    }


}