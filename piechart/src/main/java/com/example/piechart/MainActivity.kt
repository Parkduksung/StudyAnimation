package com.example.piechart

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.piechart.databinding.ActivityMainBinding
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)


        val entries = ArrayList<PieEntry>()
        entries.add(PieEntry(100f))
        entries.add(PieEntry(100f))
        entries.add(PieEntry(100f))
        entries.add(PieEntry(100f))
        entries.add(PieEntry(100f))
        entries.add(PieEntry(100f))
        entries.add(PieEntry(100f))


        val pieDataSet = PieDataSet(entries, "").apply {
            valueTextSize = 0f
        }

        with(binding.pieChart) {
            data = PieData(pieDataSet)
            description.isEnabled = false
            setDrawSlicesUnderHole(true)
            isRotationEnabled = false
            animateY(1400, Easing.EaseInOutQuad)
            animate()

            setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
                override fun onValueSelected(e: Entry?, h: Highlight?) {
                    //x 값이 index 값.
                    Toast.makeText(this@MainActivity, h?.x.toString(), Toast.LENGTH_LONG).show()
                }

                override fun onNothingSelected() {
                    Log.d("결과", "onNothingSelected")
                }
            })
        }

    }
}