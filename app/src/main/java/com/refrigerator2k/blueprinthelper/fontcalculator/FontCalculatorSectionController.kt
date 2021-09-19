package com.refrigerator2k.blueprinthelper.fontcalculator

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.chip.Chip
import com.refrigerator2k.blueprinthelper.R
import com.refrigerator2k.blueprinthelper.SectionController
import com.refrigerator2k.blueprinthelper.databinding.FragmentFontCalculatorBinding

class FontCalculatorSectionController : SectionController {
    override fun onSetup(owner: Fragment) {
        Log.d("FontCalculatorSC", "onSetup")
        val view = owner.requireView().findViewById<LinearLayout>(R.id.fontCalculatorSection)
        val binding = FragmentFontCalculatorBinding.bind(view)

        val viewModel = ViewModelProvider(owner).get(FontCalculatorViewModel::class.java)
        binding.fontCalculatorViewModel = viewModel
        binding.lifecycleOwner = owner.viewLifecycleOwner

        viewModel.defaultFontSizes.observe(owner.viewLifecycleOwner, { fontSizes ->
            val cg = binding.chipGroup
            cg.removeAllViews()

            for (fontSize in fontSizes) {
                val chip = createFontChip(owner.layoutInflater, cg, fontSize)
                chip.setOnClickListener {
                    // it triggers the textChangedListener for further actions
                    binding.fontText.setText((it as Chip).text.toString())
                }

                cg.addView(chip)
            }
        })

        viewModel.unselectedFontIndex.observe(owner.viewLifecycleOwner, {
            if (it != FontCalculatorViewModel.NO_FONT_SELECTED) {
                (binding.chipGroup[it] as Chip).isChecked = false
                Log.d("FontCalculatorSC", "unselect $it")
                viewModel.deselectionHandled()
            }
        })

        viewModel.selectedFontIndex.observe(owner.viewLifecycleOwner, {
            if (it != FontCalculatorViewModel.NO_FONT_SELECTED) {
                (binding.chipGroup[it] as Chip).isChecked = true
                //val chip = chips[it]
                //chip.isChecked = true
                Log.d("FontCalculatorSC", "select $it")
            }
        })

        binding.fontText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.setFontSize(s?.toString() ?: "")
            }
        })

        binding.fontText.setText(viewModel.fontSizeStr)
    }

    private fun createFontChip(layoutInflater: LayoutInflater, root: ViewGroup, fontSize: Double): Chip {
        val chip = layoutInflater.inflate(R.layout.font_chip, root, false) as Chip
        chip.id = View.generateViewId()
        chip.text = fontSize.toString()
        return chip
    }
}