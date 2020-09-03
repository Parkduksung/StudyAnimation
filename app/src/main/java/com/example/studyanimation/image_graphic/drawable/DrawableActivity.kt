package com.example.studyanimation.image_graphic.drawable

import android.graphics.drawable.TransitionDrawable
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import com.example.studyanimation.BaseActivity
import com.example.studyanimation.R

class DrawableActivity : BaseActivity(R.layout.activity_drawable) {

    // 정적 이미지를 앱에 표시해야 할때 Drawable 클래스와 하위 클래스를 사용하여 이미지를 그릴 수 있다.

    // Drawable 을 정의하고 인스턴스화 하는 방법 2가지
    // 1. 프로젝트에 저장된 이미지 리소스를 확장한다.   => gif 는 권장하지 않고 png 권장 , jpg 허용
    // 2. 드로어블 속성을 정의하고 xml 리소스를 확장한다.

    // 도형 드로어블, NinePatch 드로어블, 맞춤 드러어블

    // 드러오블에 색조 추가

    // 이미지의 주요 색상 추출

    private lateinit var constraintLayout: ConstraintLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        //1번
//        val i = ImageView(this).apply {
//            setImageResource(R.drawable.ic_launcher_foreground)
//            contentDescription = resources.getString(R.string.my_image_desc)
//
//            // set the ImageView bounds to match the Drawable's dimensions
//            adjustViewBounds = true
//            layoutParams = ViewGroup.LayoutParams(
//                ViewGroup.LayoutParams.WRAP_CONTENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT)
//        }
//
//        // Create a ConstraintLayout in which to add the ImageView
//        constraintLayout = ConstraintLayout(this).apply {
//
//            // Add the ImageView to the layout.
//            addView(i)
//        }
//
//        // Set the layout as the content view.
//        setContentView(constraintLayout)


//        //2번
//        val transition = ResourcesCompat.getDrawable(
//            resources,
//            R.drawable.expand_collapse,
//            null
//        ) as TransitionDrawable
//
//        val image: ImageView = findViewById(R.id.toggle_image)
//        image.setImageDrawable(transition)
//
//        // Description of the initial state that the drawable represents.
//        image.contentDescription = resources.getString(R.string.collapsed)
//
//        // Then you can call the TransitionDrawable object's methods.
//        transition.startTransition(1000)
//
//        // After the transition is complete, change the image's content description
//        // to reflect the new state.
//

    }
}