package com.example.studyanimation.opengl.lecture

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.opengl.GLES20
import android.opengl.GLException
import android.opengl.GLSurfaceView
import android.os.Bundle
import android.util.Log
import com.example.studyanimation.App
import com.example.studyanimation.R
import kotlinx.android.synthetic.main.activity_opengl.*
import java.io.IOException
import java.nio.IntBuffer
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10


class MainGLActivity : Activity(), GLSurfaceView.Renderer {

    private lateinit var mTex: Tex

    private var width: Int = 0
    private var height: Int = 0


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
        this.width = width
        this.height = height
    }

    override fun onDrawFrame(gl: GL10?) {
        GLES20.glClear(GL10.GL_COLOR_BUFFER_BIT)
        GLES20.glClearColor(1.0f, 1.0f, 1.0f, 1.0f)
        mTex.draw()

        Log.d("결과","결과")

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


    private fun createBitmapFromGLSurface(x: Int, y: Int, w: Int, h: Int, gl: GL10): Bitmap? {
        val bitmapBuffer = IntArray(w * h)
        val bitmapSource = IntArray(w * h)
        val intBuffer: IntBuffer = IntBuffer.wrap(bitmapBuffer)
        intBuffer.position(0)
        try {
            gl.glReadPixels(x, y, w, h, GL10.GL_RGBA, GL10.GL_UNSIGNED_BYTE, intBuffer)
            var offset1: Int
            var offset2: Int
            for (i in 0 until h) {
                offset1 = i * w
                offset2 = (h - i - 1) * w
                for (j in 0 until w) {
                    val texturePixel = bitmapBuffer[offset1 + j]
                    val blue = texturePixel shr 16 and 0xff
                    val red = texturePixel shl 16 and 0x00ff0000
                    val pixel = texturePixel and -0xff0100 or red or blue
                    bitmapSource[offset2 + j] = pixel
                }
            }
        } catch (e: GLException) {
            Log.e("TAG", "createBitmapFromGLSurface: " + e.message, e)
            return null
        }
        return Bitmap.createBitmap(bitmapSource, w, h, Bitmap.Config.ARGB_8888)
    }
}