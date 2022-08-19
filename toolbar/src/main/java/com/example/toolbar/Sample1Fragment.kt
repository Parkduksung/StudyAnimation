package com.example.toolbar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.toolbar.databinding.FragmentSample1Binding

class Sample1Fragment : Fragment() {

    private lateinit var binding: FragmentSample1Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sample1, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as? MainActivity)?.apply {
            setToolbar("Sample1Fragment", isVisibleLeftButton = true, isVisibleRightButton = false)

            layoutToolbarBinding.containerToolbar.toolbar.setNavigationOnClickListener {
                findNavController().navigate(R.id.action_frg_sample1_to_frg_sample2)
            }
        }
    }
}