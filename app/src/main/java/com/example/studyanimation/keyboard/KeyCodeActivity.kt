package com.example.studyanimation.keyboard

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import com.example.studyanimation.R

class KeyCodeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_key_code)


    }

    override fun dispatchKeyEvent(event: KeyEvent?): Boolean {
        event?.let {
            Log.d("결과", it.keyCode.toString())
        }
        return super.dispatchKeyEvent(event)
    }

}