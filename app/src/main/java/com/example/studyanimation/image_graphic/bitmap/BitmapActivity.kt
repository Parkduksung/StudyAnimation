package com.example.studyanimation.image_graphic.bitmap

import android.os.Bundle
import com.example.studyanimation.BaseActivity
import com.example.studyanimation.R

class BitmapActivity : BaseActivity(R.layout.activity_bitmap) {

    // 앱에서는 많은 메모리가 소요되고 UI스레드로 로드하면 앱 성능이 저하되어 응답이 느려지거나 ANR 메세지가 전송될 수 있어서...
    // 결론은 비트맵 사용할때는 스레드를 적절하게 관리하는것이 중요함.
    // 대부분의 경우 Glide 라이브러리 사용 추천함.


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}