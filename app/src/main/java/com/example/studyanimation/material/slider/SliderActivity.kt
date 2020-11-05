package com.example.studyanimation.material.slider

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.studyanimation.R
import com.google.android.material.slider.Slider
import kotlinx.android.synthetic.main.activity_slider.*

//이거 쓸려면 theme 를 material 로 바꿔줘야한다.
class SliderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slider)

        Log.d("slider value ", slider.value.toString())

        slider.addOnSliderTouchListener(object : Slider.OnSliderTouchListener {
            override fun onStartTrackingTouch(slider: Slider) {
                Log.d("value!", slider.value.toString())
            }

            override fun onStopTrackingTouch(slider: Slider) {
                Log.d("value!", slider.value.toString())
            }
        })

        slider.addOnChangeListener { slider, value, fromUser ->
            Log.d("value!", slider.value.toString())
        }

    }
}