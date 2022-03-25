package com.example.backgroundfloatingbutton

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.PixelFormat
import android.os.Handler
import android.util.Log
import android.view.*
import android.widget.ImageView

class FloatingButton @JvmOverloads constructor(private val context: Context) {

    private lateinit var windowManager: WindowManager

    private lateinit var view: View

    private var startX = 0.0f
    private var startY = 0.0f

    private var endX = 0
    private var endY = 0

    private lateinit var params: WindowManager.LayoutParams

    private fun getParams() {
        params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
            PixelFormat.TRANSLUCENT
        ).apply {
            gravity = Gravity.CENTER
        }
    }

    init {
        initView()
    }

    private fun initView() {

        view = LayoutInflater.from(context).inflate(R.layout.exit_button_layout, null, false)
        val exitButton = view.findViewById<ImageView>(R.id.exit_button)

        windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager

        getParams()
        windowManager.addView(view, params)
//        view.setOnClickListener()

        view.setOnTouchListener(viewTouchListener)
        windowManager.updateViewLayout(view, params)

    }

    var x = 0
    var y = 0


    private val viewTouchListener = View.OnTouchListener { _, event ->

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                startX = event.rawX
                startY = event.rawY
                endX = params.x
                endY = params.y
            }
            MotionEvent.ACTION_MOVE -> {
                x = (event.rawX - startX).toInt()
                y = (event.rawY - startY).toInt()
                params.x = endX + x
                params.y = endX + y
                windowManager.updateViewLayout(view, params)

            }
            MotionEvent.ACTION_POINTER_UP -> {
                x = (event.rawX - startX).toInt()
                y = (event.rawY - startY).toInt()
            }
        }

        true
    }

}