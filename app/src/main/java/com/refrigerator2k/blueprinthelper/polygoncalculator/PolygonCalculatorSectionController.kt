package com.refrigerator2k.blueprinthelper.polygoncalculator

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.refrigerator2k.blueprinthelper.R
import com.refrigerator2k.blueprinthelper.SectionController
import com.refrigerator2k.blueprinthelper.databinding.FragmentPolygonCalculatorBinding
import com.refrigerator2k.blueprinthelper.simpleTextWatcher

class PolygonCalculatorSectionController : SectionController {
    override fun onSetup(owner: Fragment) {
        val view = owner.requireView().findViewById<View>(R.id.polygonCalculatorSection)
        val binding = FragmentPolygonCalculatorBinding.bind(view)

        val viewModel = ViewModelProvider(owner).get(PolygonCalculatorViewModel::class.java)

        binding.polygonCalculatorViewModel = viewModel
        binding.lifecycleOwner = owner

        binding.radiusText.addTextChangedListener(simpleTextWatcher {
            viewModel.setRadius(it.toString().toDoubleOrNull() ?: 0.0)
        })

        binding.anglesCountText.addTextChangedListener(simpleTextWatcher {
            viewModel.setAnglesCount(it.toString().toIntOrNull() ?: 0)
        })
    }
}