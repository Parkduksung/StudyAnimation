package com.example.tooltip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.skydoves.balloon.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val balloon = createBalloon(baseContext) {
            setArrowSize(10)
            setWidth(160)
            setHeight(85)
            setArrowPosition(0.5f)
            setCornerRadius(4f)
            setLayout(R.layout.layout_custom_tooltip)
            setDismissWhenTouchOutside(false)
            setBalloonAnimation(BalloonAnimation.FADE)
            setLifecycleOwner(lifecycleOwner)
        }

        balloon.getContentView().findViewById<TextView>(R.id.tv_not_view).setOnClickListener {
            balloon.dismiss()
        }

        findViewById<TextView>(R.id.tv_sample).apply {
            showAlignBottom(balloon)
        }

    }
}