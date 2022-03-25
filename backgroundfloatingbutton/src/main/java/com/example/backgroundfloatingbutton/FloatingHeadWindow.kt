package com.example.backgroundfloatingbutton

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.PixelFormat
import android.os.Build
import android.provider.Settings
import android.view.Gravity
import android.view.LayoutInflater
import android.view.WindowManager

class FloatingHeadWindow(private val context: Context) : FloatingView.Callbacks {

    private var mWindowManager: WindowManager =
        context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    private lateinit var mLayoutParams: WindowManager.LayoutParams
    private lateinit var floatingView: FloatingView
    private var mViewAdded = false

    fun show() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Settings.canDrawOverlays(context)) {
                mWindowManager.addView(floatingView, mLayoutParams)
            }
        } else {
            mWindowManager.addView(floatingView, mLayoutParams)
        }
        mViewAdded = true
    }

    fun hide() {
        mWindowManager.removeView(floatingView)
        mViewAdded = false
    }

    fun create() {
        if (!mViewAdded) {
            floatingView = LayoutInflater.from(context)
                .inflate(R.layout.item_floating, null, false) as FloatingView
            floatingView.setCallbacks(this)
        }
    }

    fun createLayoutParams() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mLayoutParams = WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        or WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
                PixelFormat.TRANSLUCENT
            )
        } else {
            mLayoutParams = WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        or WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
                PixelFormat.TRANSLUCENT
            )
        }
        mLayoutParams.gravity = Gravity.TOP and Gravity.LEFT
        mLayoutParams.x = -floatingView.width
        mLayoutParams.y = -floatingView.height
    }


    private fun moveBy(dx: Int, dy: Int) {
        if (mViewAdded) {
            mLayoutParams.x += dx
            mLayoutParams.y += dy
            mWindowManager.updateViewLayout(floatingView, mLayoutParams)
        }
    }

    override fun onDrag(dx: Int, dy: Int) {
        moveBy(dx, dy)
    }

    override fun onDragEnd(dx: Int, dy: Int) {
    }

    override fun onDragStart(dx: Int, dy: Int) {
    }

    override fun onClick() {
        startActivity()
    }

    private fun startActivity() {
        try {
            val contentIntent = PendingIntent.getActivity(
                context,
                9999,
                Intent(context, MainActivity::class.java)
                    .addFlags(
                        Intent.FLAG_ACTIVITY_NEW_TASK
                                or Intent.FLAG_ACTIVITY_SINGLE_TOP
                                or Intent.FLAG_ACTIVITY_NO_USER_ACTION
                                or Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS
                    ),
                PendingIntent.FLAG_ONE_SHOT
            )
            contentIntent.send()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}