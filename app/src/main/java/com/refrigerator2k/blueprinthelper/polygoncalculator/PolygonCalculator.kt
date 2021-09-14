package com.refrigerator2k.blueprinthelper.polygoncalculator

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.refrigerator2k.blueprinthelper.R
import com.refrigerator2k.blueprinthelper.databinding.FragmentPolygonCalculatorBinding
import com.refrigerator2k.blueprinthelper.simpleTextWatcher

class PolygonCalculator : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentPolygonCalculatorBinding.inflate(inflater, container, false)

        val viewModel = ViewModelProvider(this).get(PolygonCalculatorViewModel::class.java)

        binding.polygonCalculatorViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.radiusText.addTextChangedListener(simpleTextWatcher {
            viewModel.setRadius(it.toString().toDoubleOrNull() ?: 0.0)
        })

        binding.anglesCountText.addTextChangedListener(simpleTextWatcher {
            viewModel.setAnglesCount(it.toString().toIntOrNull() ?: 0)
        })

        return binding.root
    }
}