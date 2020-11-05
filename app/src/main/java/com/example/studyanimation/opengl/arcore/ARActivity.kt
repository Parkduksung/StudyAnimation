//package com.example.studyanimation.opengl.arcore
//
//import android.Manifest
//import android.content.Context
//import android.content.pm.PackageManager
//import android.hardware.display.DisplayManager
//import android.hardware.display.DisplayManager.DisplayListener
//import android.os.Bundle
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.app.ActivityCompat
//import androidx.core.content.ContextCompat
//import com.example.studyanimation.R
//
//class ARActivity : AppCompatActivity() {
//
//
//    private lateinit var mFrame : Frame
//
//    private lateinit var mSession: Session
//
//    private lateinit var mConfig: Config
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_arcore)
//
//        val displayManager =
//            getSystemService(Context.DISPLAY_SERVICE) as DisplayManager
//        displayManager.registerDisplayListener(object : DisplayListener {
//            override fun onDisplayAdded(displayId: Int) {}
//            override fun onDisplayChanged(displayId: Int) {
//                synchronized(this) { mRenderer.onDisplayChanged() }
//            }
//
//            override fun onDisplayRemoved(displayId: Int) {}
//        }, null)
//
//
//        mSession
//
//    }
//
//
//    private fun requestCameraPermission() {
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
//            !== PackageManager.PERMISSION_GRANTED
//        ) {
//            ActivityCompat.requestPermissions(
//                this,
//                arrayOf(Manifest.permission.CAMERA),
//                0
//            )
//        }
//    }
//
//
//}