package com.example.piechart

import android.os.Bundle
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.ColorUtils
import androidx.databinding.DataBindingUtil
import com.example.piechart.databinding.ActivityMainBinding
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val charts = mutableListOf<PieChart>()

    private lateinit var mainPieChart: PieChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)

        initializeMainPieChart()

        binding.containerChart.addView(mainPieChart)

        binding.btnInit.setOnClickListener {
            addInnerPieChart()
        }

    }

    private fun addInnerPieChart(){
        val lasChart = charts[charts.size-1]


//        lasChart.holeRadius = 55f
//        lasChart.transparentCircleRadius = 55f
        lasChart.invalidate()

        val width = (lasChart.width * 0.5).toInt()
        val height = (lasChart.height * 0.5).toInt()

        val innerChart = createPieChart(10f)
        binding.containerChart.addView(innerChart)

        // Center & set the width and height parameters of the Inner Chart
        val params = innerChart
            .layoutParams as RelativeLayout.LayoutParams
        params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE)
        params.width = width
        params.height = height
        innerChart.layoutParams = params


        // Add Inner Chart to charts list

        // Add Inner Chart to charts list
        charts.add(innerChart)
    }

    private fun initializeMainPieChart() {
        mainPieChart = createPieChart(10f)

        mainPieChart.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

        charts.add(mainPieChart)
    }


    private fun createPieChart(sliceSpace : Float): PieChart {

        val pieChart = PieChart(this)

        val entries = ArrayList<PieEntry>()

        var colorList =
            resources.getIntArray(R.array.array_background_according_code).toMutableList()

        colorList.forEach {
            entries.add(PieEntry(10f))
        }

        val pieDataSet = PieDataSet(entries, "").apply {
            valueTextSize = 0f
            colors = colorList
            this.sliceSpace = sliceSpace
        }

        with(pieChart) {
            data = PieData(pieDataSet)
            description.isEnabled = false
            transparentCircleRadius = 0f
            isRotationEnabled = false
            legend.isEnabled = false
            centerText = "hi"

            setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
                override fun onValueSelected(e: Entry?, h: Highlight?) {
                    //x 값이 index 값.
                    highlightValues(null)
                    colorList[(h!!.x).toInt()] =
                        ColorUtils.setAlphaComponent(colorList[(h.x).toInt()], 80)
                    Toast.makeText(this@MainActivity, h.x.toString(), Toast.LENGTH_LONG).show()
                    invalidate()
                }

                override fun onNothingSelected() {}
            })
        }

//        binding.btnInit.setOnClickListener {
//            colorList =
//                resources.getIntArray(R.array.array_background_according_code).toMutableList()
//            pieDataSet.colors = colorList
//            pieChart.invalidate()
//        }
        return pieChart
    }
}