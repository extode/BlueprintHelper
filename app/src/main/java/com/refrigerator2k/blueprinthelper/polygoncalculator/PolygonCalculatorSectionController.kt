package com.refrigerator2k.blueprinthelper.polygoncalculator

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.refrigerator2k.blueprinthelper.MainFragmentDirections
import com.refrigerator2k.blueprinthelper.R
import com.refrigerator2k.blueprinthelper.SectionController
import com.refrigerator2k.blueprinthelper.databinding.FragmentPolygonCalculatorBinding
import com.refrigerator2k.blueprinthelper.simpleTextWatcher
import com.refrigerator2k.blueprinthelper.ui.SectionView

class PolygonCalculatorSectionController : SectionController() {

    override fun onSetup(owner: Fragment) {
        val view = owner.requireView()
            .findViewById<View>(R.id.polygonCalculatorSectionContent)
        val binding = FragmentPolygonCalculatorBinding.bind(view)

        val viewModel =
            ViewModelProvider(owner.requireActivity()).get(PolygonCalculatorViewModel::class.java)

        binding.polygonCalculatorViewModel = viewModel
        binding.lifecycleOwner = owner.viewLifecycleOwner
        binding.radiusText.isSaveEnabled = false
        binding.anglesCountText.isSaveEnabled = false

        binding.radiusText.setText(viewModel.radiusStr)
        binding.anglesCountText.setText(viewModel.anglesCountStr)

        binding.radiusText.addTextChangedListener(simpleTextWatcher {
            viewModel.setRadius(it.toString())
        })

        binding.anglesCountText.addTextChangedListener(simpleTextWatcher {
            viewModel.setAnglesCount(it.toString().toIntOrNull() ?: 0)
        })

        if (isExpanded) {
            viewModel.anglesCount.observe(owner.viewLifecycleOwner, { angles ->
                binding.polygonView.verticesCount = angles
            })
        }

        if (!isExpanded) {
            val sectionView = owner.requireView().findViewById(R.id.polygonCalculatorSectionView) as SectionView
            sectionView.setOnExpandAction {
                owner.findNavController().navigate(
                    MainFragmentDirections.actionMainFragment2ToPolygonCalculatorFragment()
                )
            }
        }
    }
}