package com.example.toolbar

import android.os.Bundle
import androidx.core.view.isVisible
import com.example.toolbar.base.BaseToolbarActivity
import com.example.toolbar.databinding.ActivityMainBinding

class MainActivity : BaseToolbarActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentViewBinding<ActivityMainBinding>(R.layout.activity_main)

    }
}