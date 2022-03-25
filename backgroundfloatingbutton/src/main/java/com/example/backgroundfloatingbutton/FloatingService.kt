package com.example.backgroundfloatingbutton

import android.app.Service
import android.content.Intent
import android.os.IBinder

class FloatingService : Service() {

    lateinit var floatingHeadWindow: FloatingHeadWindow

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        init()
        return super.onStartCommand(intent, flags, startId)
    }

    private fun init() {
        if (!::floatingHeadWindow.isInitialized) {
            floatingHeadWindow = FloatingHeadWindow(
                applicationContext
            ).apply {
                create()
                createLayoutParams()
                show()
            }
        }
    }
}