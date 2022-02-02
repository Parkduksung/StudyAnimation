package com.example.piechart

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.piechart.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)

        val itemList = arrayListOf<ValueItem>()

        val item1 = ValueItem("", 10f, Color.RED)
        val item2 = ValueItem("", 10f, Color.YELLOW)
        val item3 = ValueItem("", 10f, Color.GREEN)
        val item4 = ValueItem("", 10f, Color.GRAY)
        val item5 = ValueItem("", 10f, Color.BLACK)

        itemList.add(item1)
        itemList.add(item2)
        itemList.add(item3)
        itemList.add(item4)
        itemList.add(item5)

        binding.pieChart.setValueList(itemList)

    }
}