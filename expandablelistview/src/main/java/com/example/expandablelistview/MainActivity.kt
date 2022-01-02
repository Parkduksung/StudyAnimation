package com.example.expandablelistview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.expandablelistview.adapter.DetailItem
import com.example.expandablelistview.adapter.Exam
import com.example.expandablelistview.adapter.ExamAdapter
import com.example.expandablelistview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val examAdapter = ExamAdapter()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initUi()
    }

    private fun initUi() {
        binding.rvExam.run {
            adapter = examAdapter
        }
        examAdapter.addAll(mockExamList)
        examAdapter.setOnClickListener {
            examAdapter.toggleExpand(it)
        }
    }

    companion object {

        private val mockExamList = mutableListOf<Exam>().apply {
            for (i in 0..50) {
                add(Exam(i, "Name is $i", "Memo is $i"))
            }
            this[0] = this[0].copy(detailList = listOf(DetailItem("1", "1"), DetailItem("2", "2")))
        }
    }
}