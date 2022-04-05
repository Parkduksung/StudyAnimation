package com.example.wordle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.wordle.adapter.*
import com.example.wordle.databinding.ActivityMainBinding
import java.io.IOException
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val selectAdapter by lazy { SelectAdapter() }
    private val greenAdapter by lazy { ColorAdapter() }
    private val grayAdapter by lazy { ColorAdapter() }
    private val yellowAdapter by lazy { ColorAdapter() }

    private var result: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)
        result = getRandomStringFromAsset()

        with(binding) {
            rvInput.adapter = selectAdapter
            rvGreen.adapter = greenAdapter
            rvYellow.adapter = yellowAdapter
            rvGray.adapter = grayAdapter

            btnSummit.setOnClickListener {
                if (etInput.text.toString().length == 5) {
                    if (getListFromAsset()!!.contains(etInput.text.toString())) {
                        val getConvertList = ConvertUtil.convertList(
                            result!!,
                            etInput.text.toString()
                        )
                        selectAdapter.add(getConvertList) { isAdd ->
                            if (isAdd) {
                                getConvertList.forEach {
                                    when (it.first) {
                                        is Color.Yellow -> {
                                            yellowAdapter.add(it) {}
                                        }
                                        is Color.Gray -> {
                                            grayAdapter.add(it) {}
                                        }
                                        is Color.Green -> {
                                            greenAdapter.add(it) {}
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        Toast.makeText(this@MainActivity, "Not In Dictionary", Toast.LENGTH_SHORT)
                            .show()
                    }

                } else {
                    Toast.makeText(this@MainActivity, "Not 5 Letter", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }


    private fun getRandomStringFromAsset(): String? {
        return try {
            val inputStream = assets.open("wordlist.txt")
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer).toString()
            val list = String(buffer).split(",").map { it.trim().substring(1, 6) }
            val randomNum = Random.nextInt(list.size)
            list[randomNum]
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }
    private fun getListFromAsset(): List<String>? {
        return try {
            val inputStream = assets.open("wordlist.txt")
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer).toString()
            String(buffer).split(",").map { it.trim().substring(1, 6) }
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }
}