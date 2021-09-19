package com.refrigerator2k.blueprinthelper

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.refrigerator2k.blueprinthelper.fontcalculator.FontCalculatorSectionController
import com.refrigerator2k.blueprinthelper.markupbuilder.MarkupPreferencesSectionController
import com.refrigerator2k.blueprinthelper.polygoncalculator.PolygonCalculatorSectionController

class MainFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val controllers = SectionControllersProvider.get()
        for (controller in controllers) {
            controller.onSetup(this@MainFragment)
        }
        super.onViewCreated(view, savedInstanceState)
    }
}