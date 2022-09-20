package com.example.circlemenu

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.circlemenu.databinding.ActivityMainBinding
import com.ramotion.circlemenu.CircleMenuView

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)


        binding.circleMenu.eventListener = object : CircleMenuView.EventListener() {
            override fun onMenuOpenAnimationStart(view: CircleMenuView) {
                Log.d("결과", "onMenuOpenAnimationStart")
            }

            override fun onMenuOpenAnimationEnd(view: CircleMenuView) {
                Log.d("결과", "onMenuOpenAnimationEnd")
            }

            override fun onMenuCloseAnimationStart(view: CircleMenuView) {
                Log.d("결과", "onMenuCloseAnimationStart")
            }

            override fun onMenuCloseAnimationEnd(view: CircleMenuView) {
                Log.d("결과", "onMenuCloseAnimationEnd")
            }

            override fun onButtonClickAnimationStart(view: CircleMenuView, index: Int) {
                Log.d("결과", "onButtonClickAnimationStart| index: $index")
            }

            override fun onButtonClickAnimationEnd(view: CircleMenuView, index: Int) {
                Log.d("결과", "onButtonClickAnimationEnd| index: $index")
            }

            override fun onButtonLongClick(view: CircleMenuView, index: Int): Boolean {
                Log.d("결과", "onButtonLongClick| index: $index")
                return true
            }

            override fun onButtonLongClickAnimationStart(view: CircleMenuView, index: Int) {
                Log.d("결과", "onButtonLongClickAnimationStart| index: $index")
            }

            override fun onButtonLongClickAnimationEnd(view: CircleMenuView, index: Int) {
                Log.d("결과", "onButtonLongClickAnimationEnd| index: $index")
            }
        }
    }
}