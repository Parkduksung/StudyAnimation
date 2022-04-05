package com.example.wordle.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wordle.R
import com.example.wordle.constant.Color
import com.example.wordle.databinding.ActivityMainBinding
import com.example.wordle.ui.adapter.ColorAdapter
import com.example.wordle.ui.adapter.SelectAdapter
import com.example.wordle.util.InjectUtil

class MainActivity : AppCompatActivity() {

    private val mainViewModel by lazy {
        ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                    return MainViewModel(InjectUtil.provideAssetRepository(this@MainActivity)) as T
                } else throw  IllegalArgumentException()
            }
        }).get(MainViewModel::class.java)
    }

    private lateinit var binding: ActivityMainBinding

    private val selectAdapter by lazy { SelectAdapter() }
    private val greenAdapter by lazy { ColorAdapter() }
    private val grayAdapter by lazy { ColorAdapter() }
    private val yellowAdapter by lazy { ColorAdapter() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUi()
        initViewModel()
    }

    private fun initUi() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)
        with(binding) {
            rvInput.adapter = selectAdapter
            rvGreen.adapter = greenAdapter
            rvYellow.adapter = yellowAdapter
            rvGray.adapter = grayAdapter
        }
    }

    private fun initViewModel() {
        binding.viewModel = mainViewModel

        mainViewModel.mainViewStateLiveData.observe(this) { viewState ->
            when (viewState) {
                is MainViewState.Yield -> {
                    selectAdapter.add(viewState.list) { isAdd ->
                        if (isAdd) {
                            viewState.list.forEach {
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
                }
                is MainViewState.Error -> {
                    Toast.makeText(this@MainActivity, viewState.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}