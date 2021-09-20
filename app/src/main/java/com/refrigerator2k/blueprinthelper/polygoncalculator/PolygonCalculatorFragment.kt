package com.refrigerator2k.blueprinthelper.polygoncalculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.refrigerator2k.blueprinthelper.R
import com.refrigerator2k.blueprinthelper.ui.PolygonView

class PolygonCalculatorFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_polygon_calculator, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<View>(R.id.polygonView).visibility = View.VISIBLE

        PolygonCalculatorSectionController()
            .apply { isExpanded = true }
            .onSetup(this)
    }
}