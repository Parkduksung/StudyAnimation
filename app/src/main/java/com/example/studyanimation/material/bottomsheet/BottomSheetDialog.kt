package com.example.studyanimation.material.bottomsheet

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.studyanimation.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.item_bottom_sheet.*

class BottomSheetDialog : BottomSheetDialogFragment(), View.OnClickListener {

    interface SelectColorListener {
        fun setColor(color: String)
    }

    private lateinit var selectColorListener: SelectColorListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as? SelectColorListener)?.let {
            selectColorListener = it
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.black -> {
                selectColorListener.setColor("Black")
                dismiss()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(
            R.layout.item_bottom_sheet,
            container, false
        )


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        black.setOnClickListener(this)
    }


}