package com.example.studyanimation.opengl.lecture

import android.content.Context
import android.graphics.BitmapFactory
import android.opengl.GLES20
import android.opengl.GLSurfaceView
import com.example.studyanimation.App
import java.io.IOException
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class MainGLRenderer : GLSurfaceView.Renderer {
    private var mTex: Tex? = null
    override fun onDrawFrame(unused: GL10) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT)
        GLES20.glClearColor(1.0f, 1.0f, 1.0f, 1.0f)
        mTex!!.draw()
    }

    override fun onSurfaceCreated(gl: GL10, config: EGLConfig) {
        init()
    }

    override fun onSurfaceChanged(unused: GL10, width: Int, height: Int) {
        GLES20.glViewport(0, 0, width, height)
    }

    private fun init() {
        try {
            val inputStream = App.instance.assets.open("image.png")
            val bitmap = BitmapFactory.decodeStream(inputStream)
            mTex = Tex(bitmap)
        } catch (e: IOException) {
            e.printStackTrace()
        }

//        mTex = new Tex(this, image);
    }

//    companion object {
//        @JvmStatic
//        fun loadShader(type: Int, shaderCode: String?): Int {
//            val shader = GLES20.glCreateShader(type)
//            GLES20.glShaderSource(shader, shaderCode)
//            GLES20.glCompileShader(shader)
//            return shader
//        }
//    }
}