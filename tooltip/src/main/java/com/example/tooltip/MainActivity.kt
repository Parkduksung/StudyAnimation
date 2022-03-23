package com.example.tooltip

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.*
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.tooltip.tooltip.Tooltip
import com.skydoves.balloon.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

        Tooltip.Builder(findViewById<TextView>(R.id.tv_sample)).apply {
            setText(ssb).setCornerRadius(20.0f)
            setArrow(R.drawable.ic_tootip_top_arrow)
            setTextColor(Color.WHITE)
            setBackgroundColor(Color.BLACK)
            show()
        }


        val spannableString = SpannableString("abc")

        val drawable = ContextCompat.getDrawable(this, R.drawable.tooltip_close2)
        drawable?.setBounds(0,0,findViewById<TextView>(R.id.tv_sample).width, findViewById<TextView>(R.id.tv_sample).height)
        ImageSpan(drawable!!, ImageSpan.ALIGN_BASELINE)

        spannableString.setSpan(ImageSpan(drawable, ImageSpan.ALIGN_BASELINE),0,1,Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        findViewById<TextView>(R.id.tv_sample).text = spannableString
    }
}