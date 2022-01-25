package com.example.calendar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.show_calendar).setOnClickListener {
            showCalendar()
        }

    }

    private fun showCalendar() {


        val calendar1 = Calendar.getInstance()
        val calendar2 = Calendar.getInstance()
        calendar2.set(2040, 11, 31)
        val upTo = calendar2.timeInMillis
        calendar1.set(2020, 0, 1)
        val startFrom = calendar1.timeInMillis
        val constraints = CalendarConstraints.Builder()
            .setStart(startFrom)
            .setEnd(upTo)
            .build()

        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setCalendarConstraints(constraints)
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build().apply {
                    addOnPositiveButtonClickListener {
                        val startDate = SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(it)
                        Log.d("결과", startDate)
                    }
                }
        datePicker.show(supportFragmentManager, "")

    }
}