package com.example.studyanimation.orientation

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import com.example.studyanimation.BaseActivity
import com.example.studyanimation.R

class OrientationActivity : BaseActivity(R.layout.activity_orientation) {


    private val orientationViewModel: OrientationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        orientationViewModel.viewStateLiveData.observe(this, { viewState ->

            requestedOrientation = when (viewState) {

                is OrientationViewModel.ViewState.RotationPort -> {
                    ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                }

                is OrientationViewModel.ViewState.RotationLand -> {
                    ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                }

                else -> {
                    ActivityInfo.SCREEN_ORIENTATION_LOCKED
                }
            }

        })

        findViewById<Button>(R.id.rotation_button).setOnClickListener {
            orientationViewModel.changeOrientation()
        }
    }
}