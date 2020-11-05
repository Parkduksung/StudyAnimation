package com.example.studyanimation.material.bottomsheet

import android.os.Bundle
import android.widget.Toast
import com.example.studyanimation.BaseActivity
import com.example.studyanimation.R
import kotlinx.android.synthetic.main.activity_bottom_sheet.*

class BottomSheetActivity : BaseActivity(R.layout.activity_bottom_sheet),
    BottomSheetDialog.SelectColorListener {


    override fun setColor(color: String) {
        Toast.makeText(this, color, Toast.LENGTH_SHORT).show()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        button.setOnClickListener {
            BottomSheetDialog().show(
                supportFragmentManager,
                "ModalBottomSheet"
            )

        }
    }
}