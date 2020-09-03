package com.example.studyanimation.image_graphic.opengl

import android.opengl.GLES20
import android.opengl.GLSurfaceView
import android.opengl.Matrix
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class MyGLRenderer : GLSurfaceView.Renderer {

    private lateinit var mTriangle: Triangle
    private lateinit var mSquare: Square

    private val vPMatrix = FloatArray(16)
    private val projectionMatrix = FloatArray(16)
    private val viewMatrix = FloatArray(16)

    //참고: OpenGL ES 2.0 API를 사용할 때 이러한 메서드에 GL10 매개변수가 있는 이유를 궁금해할 수 있습니다.
    //이러한 메서드 서명은 단순히 2.0 API에서 Android 프레임워크 코드를 더 간단하게 유지하기 위해 재사용합니다.
    override fun onSurfaceCreated(unused: GL10, config: EGLConfig) {
        // initialize a triangle

        mTriangle = Triangle()
        // initialize a square
        mSquare = Square()
    }

    override fun onDrawFrame(unused: GL10) {

        Matrix.setLookAtM(viewMatrix, 0, 0f, 0f, -3f, 0f, 0f, 0f, 0f, 1.0f, 0.0f)

        // Calculate the projection and view transformation
        Matrix.multiplyMM(vPMatrix, 0, projectionMatrix, 0, viewMatrix, 0)


        mTriangle.draw(vPMatrix)
    }

    override fun onSurfaceChanged(unused: GL10, width: Int, height: Int) {
        GLES20.glViewport(0, 0, width, height)

        val ratio: Float = width.toFloat() / height.toFloat()

        // this projection matrix is applied to object coordinates
        // in the onDrawFrame() method
        Matrix.frustumM(projectionMatrix, 0, -ratio, ratio, -1f, 1f, 3f, 7f)
    }


}