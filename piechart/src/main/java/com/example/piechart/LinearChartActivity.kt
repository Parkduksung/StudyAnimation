package com.example.piechart

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.piechart.databinding.ActivityLinearBinding
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet

class LinearChartActivity : AppCompatActivity() {


    private lateinit var binding: ActivityLinearBinding
    private var floatTemp: Float? = 10f


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_linear)
        setContentView(binding.root)
        setup()

    }

    private fun setup() {
        val xAxis = binding.lineChart.xAxis

        xAxis.apply {
            position = XAxis.XAxisPosition.BOTTOM
            textSize = 10f
            setDrawGridLines(false)
            granularity = 1f
            axisMinimum = 2f
            isGranularityEnabled = true
        }

        binding.lineChart.apply {
            axisRight.isEnabled = true
            axisLeft.axisMaximum = 50f
            legend.apply {
                textSize = 15f
                verticalAlignment = Legend.LegendVerticalAlignment.TOP
                horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
                orientation = Legend.LegendOrientation.HORIZONTAL
                setDrawInside(false)
            }
        }

        val lineData = LineData()
        binding.lineChart.data = lineData
        feedMultiple()
    }

    private var thread: Thread? = null

    private fun feedMultiple() {
        if (thread != null) {
            thread!!.interrupt()
        }

        val runnable = Runnable {
            addEntry()
        }
        thread = Thread(Runnable {
            while (true) {
                runOnUiThread(runnable)
                try {
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        })
        thread!!.start()
    }

    private fun addEntry() {
        val data = binding.lineChart.data

        data?.let {
            var set: ILineDataSet? = data.getDataSetByIndex(0)
            if (set == null) {
                set = createSet()
                data.addDataSet(set)
            }
            floatTemp = ((20..40).random()).toFloat()
            data.addEntry(Entry(set.entryCount.toFloat(), floatTemp!!), 0)
            data.notifyDataChanged()
            binding.lineChart.apply {
                notifyDataSetChanged()
                moveViewToX(data.entryCount.toFloat())
                setVisibleXRangeMaximum(4f)
                setPinchZoom(false)
                isDoubleTapToZoomEnabled = false
                description.text = "시간"
                description.textSize = 15f
                setExtraOffsets(8f, 16f, 8f, 16f)
            }
        }
    }

    private fun createSet(): ILineDataSet {
        val set = LineDataSet(null, "체온 (C)")
        set.apply {
            axisDependency = YAxis.AxisDependency.LEFT
            color = Color.DKGRAY
            setCircleColor(Color.GREEN)
            valueTextSize = 10f
            lineWidth = 2f
            circleRadius = 3f
            fillAlpha = 0
            fillColor = Color.GREEN
            highLightColor = Color.BLACK
            setDrawValues(true)
        }
        return set
    }

}