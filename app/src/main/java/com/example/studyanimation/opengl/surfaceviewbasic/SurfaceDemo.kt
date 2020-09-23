package com.example.studyanimation.opengl.surfaceviewbasic

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.SurfaceHolder
import com.example.studyanimation.BaseActivity
import com.example.studyanimation.R
import kotlinx.android.synthetic.main.activity_surfaceview_basic.*


class SurfaceDemo : BaseActivity(R.layout.activity_surfaceview_basic), SurfaceHolder.Callback {
    /**
     * Called when the activity is first created.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        surface_view.holder.addCallback(this)
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        tryDrawing(holder)
    }

    override fun surfaceChanged(holder: SurfaceHolder, frmt: Int, w: Int, h: Int) {
        Log.d("결과", "결")
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {}

    private fun tryDrawing(holder: SurfaceHolder) {
        val canvas = holder.lockCanvas()
        if (canvas == null) {
            Log.e(TAG, "Cannot draw onto the canvas as it's null")
        } else {
            val assetManager = assets
            val inputStream = assetManager.open("image.png")

            val bitmap = BitmapFactory.decodeStream(inputStream)

            val resultBitmap = Bitmap.createScaledBitmap(
                bitmap,
                holder.surfaceFrame.width(),
                holder.surfaceFrame.height(),
                false
            )

            canvas.drawBitmap(resultBitmap, 0f, 0f, null)

            holder.unlockCanvasAndPost(canvas)
        }
    }

    companion object {
        private const val TAG = "SurfaceDemo"
    }
}