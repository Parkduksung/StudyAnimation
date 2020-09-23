package com.example.studyanimation.image_graphic.rendering

import android.os.Bundle
import com.example.studyanimation.BaseActivity
import com.example.studyanimation.R

class RenderingActivity : BaseActivity(R.layout.activity_rendering) {

    //렌더링
    //사용자의 품질 인식에 영향을 주는 앱의 핵심 관점은 이미지와 텍스트를 화면에 부드럽게 렌더링하는 것입니다.
    //앱이 화면에 그려질 때 버벅거림과 반응이 느려지는 것을 피하는 것이 중요합니다.
    //
    //앱의 렌더링 성능을 최적화하기 위해 오버드로 줄이기, 뷰 계층 구조 최적화 및 GPU 프로파일 도구 활용 같은 몇 가지 방법에 관해 배웁니다.

    //                          작업 렌더링
    //오버드로 줄이기
    //앱이 한 프레임에 동일한 픽셀을 다시 그리는 횟수를 최소화합니다.

    //성능 및 뷰 계층 구조
    //레이아웃과 측정이 효율적으로 실행되고 이중과세가 발생하지 않는지 확인합니다.

    //GPU 렌더링 프로파일을 사용한 분석
    //이 기기 도구를 활용하여 앱의 렌더링 속도를 느려지게 할 수 있는 병목 현상을 파악합니다.


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}