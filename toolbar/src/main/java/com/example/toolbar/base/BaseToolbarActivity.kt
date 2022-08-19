package com.example.toolbar.base

import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.toolbar.R
import com.example.toolbar.databinding.LayoutToolbarBinding

abstract class BaseToolbarActivity : AppCompatActivity() {

    lateinit var layoutToolbarBinding: LayoutToolbarBinding

    open fun <T : ViewDataBinding?> setContentViewBinding(
        layoutResID: Int
    ): T {
        layoutToolbarBinding = DataBindingUtil.setContentView(this, R.layout.layout_toolbar)
        super.setContentView(layoutToolbarBinding.root)
        return DataBindingUtil.inflate(
            layoutInflater,
            layoutResID,
            layoutToolbarBinding.contentsLinearlayout,
            true
        )
    }

    fun setToolbar(content: String, isVisibleLeftButton: Boolean, isVisibleRightButton: Boolean) {
        with(layoutToolbarBinding.containerToolbar) {
            if(isVisibleLeftButton){
                toolbar.setNavigationIcon(R.drawable.ic_more)
            }else{
                toolbar.navigationIcon = null
            }
            title.text = content
        }
    }
}