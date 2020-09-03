package com.example.studyanimation.image_graphic.vector_drawable

import android.graphics.drawable.TransitionDrawable
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import com.example.studyanimation.BaseActivity
import com.example.studyanimation.R

class VectorDrawableActivity : BaseActivity(R.layout.activity_drawable) {

    //VectorDrawable은 XML 파일에서 연관된 색상 정보와 함께 점, 선, 곡선의 조합으로 정의되는 벡터 그래픽입니다
    //가장 큰 장점은 이미지 확장성입니다. 화면 품질이 손상되지 않고 크기를 조정할 수 있으므로
    // 이미지 품질을 유지하면서 다양한 화면 밀도에 맞게 동일한 파일의 크기를 조정할 수 있습니다. => APK 파일 수가 줄고 개발자 유지 관리 작업이 줄어듭니다

    //VectorDrawable 클래스 정보
    //VectorDrawable에서는 정적 드로어블 객체를 정의합니다.
    // SVG 형식과 마찬가지로 각 벡터 그래픽은 path 및 group 개체로 구성되는 트리 계층 구조로 정의됩니다.
    // 각 path에는 객체 윤곽선의 도형이 포함되고 group에는 변환에 관한 세부 정보가 포함됩니다.
    // 모든 경로는 XML 파일에 표시된 순서대로 그립니다.

    //AnimatedVectorDrawable 클래스 정보
    //AnimatedVectorDrawable에서는 벡터 그래픽의 속성에 애니메이션을 추가합니다.
    // 애니메이션 벡터 그래픽을 세 개의 개별 리소스 파일로 정의하거나 전체 드로어블을 정의하는 단일 XML 파일로 정의할 수 있습니다.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}