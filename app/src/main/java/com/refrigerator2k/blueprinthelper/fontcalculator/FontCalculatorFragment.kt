package com.refrigerator2k.blueprinthelper.fontcalculator

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.chip.Chip
import com.refrigerator2k.blueprinthelper.R
import com.refrigerator2k.blueprinthelper.databinding.FragmentFontCalculatorBinding

class FontCalculatorFragment : Fragment() {
    private val TAG = "FontCalculatorFragment"
    private lateinit var binding: FragmentFontCalculatorBinding
    private val chips = mutableListOf<Chip>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFontCalculatorBinding.inflate(inflater, container, false)

        val viewModel = ViewModelProvider(this).get(FontCalculatorViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.defaultFontSizes.observe(viewLifecycleOwner, { fontSizes ->
            val cg = binding.chipGroup
            cg.removeAllViews()

            for (fontSize in fontSizes) {
                val chip = createFontChip(cg, fontSize)
                chip.setOnClickListener {
                    // it triggers the textChangedListener for further actions
                    binding.fontText.setText((it as Chip).text.toString())
                }

                cg.addView(chip)
                chips.add(chip)
            }
        })

        viewModel.selectedFontIndex.observe(viewLifecycleOwner, {
            if (it != FontCalculatorViewModel.NO_FONT_SELECTED) {
                val chip = chips[it]
                chip.isChecked = true
            }
        })

        viewModel.unselectedFontIndex.observe(viewLifecycleOwner, {
            if (it != FontCalculatorViewModel.NO_FONT_SELECTED) {
                chips[it].isChecked = false
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

        return binding.root
    }

    private fun createFontChip(root: ViewGroup, fontSize: Double): Chip {
        val chip = layoutInflater.inflate(R.layout.font_chip, root, false) as Chip
        chip.text = fontSize.toString()
        return chip
    }
}