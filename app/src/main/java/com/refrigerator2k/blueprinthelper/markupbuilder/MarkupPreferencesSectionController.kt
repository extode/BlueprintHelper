package com.refrigerator2k.blueprinthelper.markupbuilder

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.refrigerator2k.blueprinthelper.MainActivity
import com.refrigerator2k.blueprinthelper.MainFragmentDirections
import com.refrigerator2k.blueprinthelper.R
import com.refrigerator2k.blueprinthelper.SectionController

class MarkupPreferencesSectionController : SectionController {
    override fun onSetup(owner: Fragment) {
        val view = owner.requireView().findViewById(R.id.markupPreferencesSection) as View
        val toMarkupButton = view.findViewById<Button>(R.id.toMarkupButton)
        val lineForMarkupText = view.findViewById<TextView>(R.id.lineForMarkupText)
        val sheetWidthText = view.findViewById<TextView>(R.id.areaWidthText)

        toMarkupButton.setOnClickListener {
            (owner.activity as MainActivity).hideKeyboard()

            val text = lineForMarkupText.text.toString()
            val sheetWidth = sheetWidthText.text.toString().toFloatOrNull() ?: return@setOnClickListener
            owner.findNavController().navigate(
                MainFragmentDirections.actionMainFragment2ToMarkupBuilderFragment(text, sheetWidth)
            )
        }
    }
}