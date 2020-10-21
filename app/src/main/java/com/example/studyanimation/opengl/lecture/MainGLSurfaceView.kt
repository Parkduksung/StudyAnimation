package com.example.studyanimation.opengl.lecture

import android.content.Context
import android.opengl.GLSurfaceView

class MainGLSurfaceView(context: Context?) : GLSurfaceView(context) {
    private val mRenderer: MainGLRenderer

    init {
        setEGLContextClientVersion(2)
        mRenderer = MainGLRenderer()
        setRenderer(mRenderer)
        renderMode = RENDERMODE_CONTINUOUSLY
    }
}