package com.example.piechart

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.ColorUtils
import androidx.databinding.DataBindingUtil
import com.example.piechart.databinding.ActivityMainBinding
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

        var colorList =
            resources.getIntArray(R.array.array_background_according_code).toMutableList()

        colorList.forEach {
            entries.add(PieEntry(10f))
        }

        val pieDataSet = PieDataSet(entries, "").apply {
            valueTextSize = 0f
            colors = colorList
            sliceSpace = 10f
        }

        with(binding.pieChart) {
            data = PieData(pieDataSet)
            description.isEnabled = false
            transparentCircleRadius = 0f
            legend.isEnabled = false

            setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
                override fun onValueSelected(e: Entry?, h: Highlight?) {
                    //x 값이 index 값.
                    highlightValues(null)
//                  val t  =  ColorUtils.setAlphaComponent(colorList[(h!!.x).toInt()],80)
                    colorList[(h!!.x).toInt()] =
                        ColorUtils.setAlphaComponent(colorList[(h.x).toInt()], 80)
                    Toast.makeText(this@MainActivity, h.x.toString(), Toast.LENGTH_LONG).show()
                    invalidate()
                }

                override fun onNothingSelected() {
//                    pieDataSet.colors = resources.getIntArray(R.array.array_background_according_code).toMutableList()
//                    invalidate()
//                    colorList.map { resources.getIntArray(R.array.array_background_according_code) }
                    Log.d("결과", "onNothingSelected")
                }
            })
        }

        binding.btnInit.setOnClickListener {
            colorList = resources.getIntArray(R.array.array_background_according_code).toMutableList()
            pieDataSet.colors = colorList
            binding.pieChart.invalidate()
        }

    }
}