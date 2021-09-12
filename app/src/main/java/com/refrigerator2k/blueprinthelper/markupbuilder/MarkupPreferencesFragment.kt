package com.refrigerator2k.blueprinthelper.markupbuilder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.refrigerator2k.blueprinthelper.MainActivity
import com.refrigerator2k.blueprinthelper.R

class MarkupPreferencesFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_markup_preferences, container, false)
        val toMarkupButton = view.findViewById<Button>(R.id.toMarkupButton)
        val lineForMarkupText = view.findViewById<TextView>(R.id.lineForMarkupText)
        val sheetWidthText = view.findViewById<TextView>(R.id.paperWidthText)

        toMarkupButton.setOnClickListener {
            (activity as MainActivity).hideKeyboard()

            val text = lineForMarkupText.text.toString()
            val sheetWidth = sheetWidthText.text.toString().toFloatOrNull() ?: return@setOnClickListener
            findNavController().navigate(
                MarkupPreferencesFragmentDirections.actionMarkupPreferencesFragmentToMarkupBuilderFragment(text, sheetWidth)
            )
        }

        return view
    }
}