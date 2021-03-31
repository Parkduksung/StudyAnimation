package com.example.studyanimation.orientation

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.studyanimation.BaseActivity
import com.example.studyanimation.R

class OrientationActivity : BaseActivity(R.layout.activity_orientation) {

    private var toggleRotation = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LOCKED


        findViewById<Button>(R.id.rotation_button).setOnClickListener {

            toggleRotation = !toggleRotation

            Log.d("결과", toggleRotation.toString())
            requestedOrientation = if(!toggleRotation){
                ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            }else{
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            }
        }
    }
}