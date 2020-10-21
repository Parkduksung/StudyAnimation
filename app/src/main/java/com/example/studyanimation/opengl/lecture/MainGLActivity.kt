package com.example.studyanimation.opengl.lecture

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.opengl.GLES20
import android.opengl.GLSurfaceView
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.example.studyanimation.App
import com.example.studyanimation.R
import kotlinx.android.synthetic.main.activity_opengl.*
import java.io.IOException
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class MainGLActivity : Activity(), GLSurfaceView.Renderer {

    private lateinit var mTex: Tex

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opengl)
        glsurface.setEGLContextClientVersion(2)
        glsurface.setRenderer(this)
        glsurface.renderMode = GLSurfaceView.RENDERMODE_CONTINUOUSLY
    }

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        init()
    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        GLES20.glViewport(0, 0, width, height)
    }

    override fun onDrawFrame(gl: GL10?) {
        GLES20.glClear(GL10.GL_COLOR_BUFFER_BIT)
        GLES20.glClearColor(1.0f, 1.0f, 1.0f, 1.0f)
        mTex.draw()
    }

    private fun init() {
        try {
            val inputStream = App.instance.assets.open("image.png")
            val bitmap = BitmapFactory.decodeStream(inputStream)

            mTex = Tex(bitmap)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}