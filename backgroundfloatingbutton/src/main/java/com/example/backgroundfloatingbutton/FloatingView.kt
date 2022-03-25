package com.example.backgroundfloatingbutton

import android.content.Context
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout

class FloatingView @JvmOverloads constructor(
    mContext: Context,
    attributeSet: AttributeSet? = null,
    def: Int = 0
) :
    FrameLayout(mContext, attributeSet, def), View.OnTouchListener {

    private var downRawX: Int = 0
    private var downRawY: Int = 0
    private var lastX: Int = 0
    private var lastY: Int = 0
    private lateinit var callbacks: Callbacks
    private val mGestureDetector: GestureDetector

    init {
        setOnTouchListener(this)
        mGestureDetector = GestureDetector(context, GestureListener())
    }

    fun setCallbacks(callbacks: Callbacks) {
        this.callbacks = callbacks
    }


    override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
        mGestureDetector.onTouchEvent(motionEvent)

        val x = motionEvent.rawX.toInt()
        val y = motionEvent.rawY.toInt()


        return when (motionEvent.action) {

            MotionEvent.ACTION_DOWN -> {
                downRawX = x
                downRawY = y
                lastX = x
                lastY = y
                true
            }

            MotionEvent.ACTION_MOVE -> {
                val nx = (x - lastX)
                val ny = (y - lastY)
                lastX = x
                lastY = y
                callbacks.onDrag(nx, ny)
                true
            }

            MotionEvent.ACTION_UP -> {
                callbacks.onDragEnd(x, y)
                true
            }
            else -> {
                super.onTouchEvent(motionEvent)
            }

        }
    }

    inner class GestureListener : GestureDetector.SimpleOnGestureListener() {
        override fun onSingleTapUp(e: MotionEvent?): Boolean {
            callbacks.onClick()
            return true
        }
    }


    interface Callbacks {
        fun onDrag(dx: Int, dy: Int)
        fun onDragEnd(dx: Int, dy: Int)
        fun onDragStart(dx: Int, dy: Int)
        fun onClick()
    }


}