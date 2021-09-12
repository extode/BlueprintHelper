package com.refrigerator2k.blueprinthelper.fontcalculator

import com.refrigerator2k.blueprinthelper.round2

class FontCalculatorTypeB : FontCalculator() {
    override fun calcUppercaseHeight(fontSize: Double): Double = fontSize

    override fun calcLowercaseHeight(fontSize: Double): Double = round2(7.0 / 10.0 * fontSize)

    override fun calcLetterSpacing(fontSize: Double): Double = round2(2.0 / 10.0 * fontSize)

    override fun calcMinLineHeight(fontSize: Double): Double = round2(17.0 / 10.0 * fontSize)

    override fun calcMinWordSpacing(fontSize: Double): Double = round2(6.0 / 10.0 * fontSize)

    override fun calcFontWeight(fontSize: Double): Double = round2(1.0 / 10.0 * fontSize)
}
