package com.example.studyanimation.image_graphic.opengl

import android.content.Context
import android.opengl.GLSurfaceView

class MyGLSurfaceView(context: Context) : GLSurfaceView(context) {

    private val renderer: MyGLRenderer

    init {

        // Create an OpenGL ES 2.0 context
        setEGLContextClientVersion(2)

        renderer = MyGLRenderer()


        // Set the Renderer for drawing on the GLSurfaceView
        setRenderer(renderer)


        //그리기 데이터가 변경될 때만 뷰를 그리도록 렌더링 모드를 설정하는 것입니다.
        renderMode = RENDERMODE_WHEN_DIRTY
        //이처럼 설정하면 requestRender()를 호출할 때까지 GLSurfaceView 프레임을 다시 그리지 않습니다. 그러면 이 샘플 앱에 더 효율적입니다.
    }

}
