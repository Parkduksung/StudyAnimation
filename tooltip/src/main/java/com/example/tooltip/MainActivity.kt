package com.example.tooltip

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.*
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.tooltip.tooltip.Tooltip
import com.skydoves.balloon.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val balloon = createBalloon(baseContext) {
//            setArrowSize(10)
//            setWidth(160)
//            setHeight(85)
//            setArrowPosition(0.5f)
//            setCornerRadius(4f)
//            setLayout(R.layout.layout_custom_tooltip)
//            setDismissWhenTouchOutside(false)
//            setBalloonAnimation(BalloonAnimation.FADE)
//            setLifecycleOwner(lifecycleOwner)
//        }
//
//        balloon.getContentView().findViewById<TextView>(R.id.tv_not_view).setOnClickListener {
//            balloon.dismiss()
//        }
//
//        findViewById<TextView>(R.id.tv_sample).apply {
//            showAlignBottom(balloon)
//        }

        val ssb = SpannableStringBuilder(
            "재입고 알림 신청 시 입고와 동시에\n" +
                    "알림을 받아보실 수 있습니다.\n" +
                    " X 다시 보지 않기"
        )
        ssb.apply {
            setSpan(ForegroundColorSpan(Color.WHITE), 0, ssb.length - 11, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(ForegroundColorSpan(Color.LTGRAY), ssb.length - 10, ssb.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(RelativeSizeSpan(.8f), ssb.length - 10, ssb.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        findViewById<TextView>(R.id.tv_sample).text = ssb

        Tooltip.Builder(findViewById<TextView>(R.id.tv_sample)).apply {
            setText(ssb).setCornerRadius(20.0f)
            setArrow(R.drawable.ic_tootip_top_arrow)
            setTextColor(Color.WHITE)
            setBackgroundColor(Color.BLACK)
            show()
        }


//        findViewById<TextView>(R.id.tv_sample).


    }
}