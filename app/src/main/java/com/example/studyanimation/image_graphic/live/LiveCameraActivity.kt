package com.example.studyanimation.image_graphic.live

import android.app.Activity
import android.graphics.SurfaceTexture
import android.hardware.Camera
import android.os.Bundle
import android.view.Surface
import android.view.TextureView
import android.view.TextureView.SurfaceTextureListener
import android.view.WindowManager
import android.widget.Toast
import java.io.IOException

class LiveCameraActivity : Activity(), SurfaceTextureListener {
    private var mCamera: Camera? = null
    private var mSurfaceTexture: SurfaceTexture? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val textureView = TextureView(this)
        textureView.surfaceTextureListener = this
        setContentView(textureView)
    }

    override fun onSurfaceTextureAvailable(surface: SurfaceTexture, width: Int, height: Int) {
        mSurfaceTexture = surface
        if (!PermissionHelper.hasCameraPermission(this)) {
            PermissionHelper.requestCameraPermission(this, false)
        } else {
            startPreview()
        }
    }

    override fun onSurfaceTextureSizeChanged(surface: SurfaceTexture, width: Int, height: Int) {
        surface.setDefaultBufferSize(257, 257)
        // Ignored, Camera does all the work for us
    }

    override fun onSurfaceTextureDestroyed(surface: SurfaceTexture): Boolean {
        mCamera!!.stopPreview()
        mCamera!!.release()
        return true
    }

    override fun onSurfaceTextureUpdated(surface: SurfaceTexture) {

        // Invoked every time there's a new Camera preview frame
        //Log.d(TAG, "updated, ts=" + surface.getTimestamp());
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (!PermissionHelper.hasCameraPermission(this)) {
            Toast.makeText(
                this,
                "Camera permission is needed to run this application", Toast.LENGTH_LONG
            ).show()
            PermissionHelper.launchPermissionSettings(this)
            finish()
        } else {
            startPreview()
        }
    }

    private fun startPreview() {
        mCamera = Camera.open()
        if (mCamera == null) {
            // Seeing this on Nexus 7 2012 -- I guess it wants a rear-facing camera, but
            // there isn't one.  TODO: fix
            throw RuntimeException("Default camera not available")
        }
        try {
            mCamera!!.setPreviewTexture(mSurfaceTexture)
            val display = (getSystemService(WINDOW_SERVICE) as WindowManager).defaultDisplay
            if (display.rotation == Surface.ROTATION_0) {
                mCamera!!.setDisplayOrientation(90)
            }
            if (display.rotation == Surface.ROTATION_270) {
                mCamera!!.setDisplayOrientation(180)
            }
            mCamera!!.startPreview()
        } catch (ioe: IOException) {
            // Something bad happened
//            Log.e(TAG,"Exception starting preview", ioe);
        }
    }
}