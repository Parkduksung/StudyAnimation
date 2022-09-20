package com.example.picker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.jzxiang.pickerview.TimePickerDialog
import com.jzxiang.pickerview.data.Type
import com.jzxiang.pickerview.listener.OnDateSetListener
import java.util.Calendar

class MainActivity : AppCompatActivity(), OnDateSetListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button).setOnClickListener {
            TimePickerDialog.Builder()
                .setCallBack(this)
                .setCancelStringId("Cancel")
                .setSureStringId("Sure")
                .setYearText("Year")
                .setMonthText("Month")
                .setDayText("Day")
                .setHourText("Hour")
                .setMinuteText("Minute")
                .setCyclic(false)
                .setMinMillseconds(System.currentTimeMillis() - 31557600000)
                .setMaxMillseconds(System.currentTimeMillis() + 31557600000)
                .setCurrentMillseconds(System.currentTimeMillis())
                .setThemeColor(resources.getColor(R.color.timepicker_dialog_bg))
                .setType(Type.ALL)
                .setWheelItemTextNormalColor(resources.getColor(R.color.timetimepicker_default_text_color))
                .setWheelItemTextSelectorColor(resources.getColor(R.color.timepicker_toolbar_bg))
                .setWheelItemTextSize(12)
                .build().show(supportFragmentManager, "")
        }

    }

    override fun onDateSet(timePickerView: TimePickerDialog?, millseconds: Long) {

    }
}