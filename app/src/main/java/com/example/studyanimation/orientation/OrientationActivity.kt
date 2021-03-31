package com.example.studyanimation.orientation

import android.content.pm.ActivityInfo
import android.os.Bundle
import com.example.studyanimation.BaseActivity
import com.example.studyanimation.R

class OrientationActivity : BaseActivity(R.layout.activity_orientation) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LOCKED

    }
}