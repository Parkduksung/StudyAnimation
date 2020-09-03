package com.example.studyanimation.image_graphic.opengl

import android.opengl.GLES20
import android.opengl.GLSurfaceView
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class MyGLRenderer  : GLSurfaceView.Renderer{

    private lateinit var mTriangle: Triangle
    private lateinit var mSquare: Square

    //참고: OpenGL ES 2.0 API를 사용할 때 이러한 메서드에 GL10 매개변수가 있는 이유를 궁금해할 수 있습니다.
    //이러한 메서드 서명은 단순히 2.0 API에서 Android 프레임워크 코드를 더 간단하게 유지하기 위해 재사용합니다.
    override fun onSurfaceCreated(unused: GL10, config: EGLConfig) {
        // initialize a triangle
        mTriangle = Triangle()
        // initialize a square
        mSquare = Square()
    }

    override fun onDrawFrame(unused: GL10) {
        // Redraw background color
//        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT)
        mTriangle.draw()
    }

    override fun onSurfaceChanged(unused: GL10, width: Int, height: Int) {
        GLES20.glViewport(0, 0, width, height)
    }


}