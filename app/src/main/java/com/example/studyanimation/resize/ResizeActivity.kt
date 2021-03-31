package com.example.studyanimation.resize

import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import com.example.studyanimation.BaseActivity
import com.example.studyanimation.R

class ResizeActivity : BaseActivity(R.layout.activity_resize) {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onResume() {
        super.onResume()
        Log.d("결과" , (getSystemService(WINDOW_SERVICE) as WindowManager).defaultDisplay.height.toString())
    }
}