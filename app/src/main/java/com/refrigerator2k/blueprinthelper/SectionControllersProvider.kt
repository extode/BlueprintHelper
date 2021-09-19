package com.refrigerator2k.blueprinthelper

import com.refrigerator2k.blueprinthelper.fontcalculator.FontCalculatorSectionController
import com.refrigerator2k.blueprinthelper.markupbuilder.MarkupPreferencesSectionController
import com.refrigerator2k.blueprinthelper.polygoncalculator.PolygonCalculatorSectionController

object SectionControllersProvider {
    private val controllers = arrayListOf(
        FontCalculatorSectionController(),
        MarkupPreferencesSectionController(),
        PolygonCalculatorSectionController()
    )

    fun get(): List<SectionController> = controllers
}