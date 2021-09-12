package com.refrigerator2k.blueprinthelper.fontcalculator

import com.refrigerator2k.blueprinthelper.FontProperties

abstract class FontCalculator {

    fun calculate(fontSize: Double): FontProperties {
        return FontProperties(
            fontSize,
            calcUppercaseHeight(fontSize),
            calcLowercaseHeight(fontSize),
            calcLetterSpacing(fontSize),
            calcMinLineHeight(fontSize),
            calcMinWordSpacing(fontSize),
            calcFontWeight(fontSize)
        )
    }

    abstract fun calcUppercaseHeight(fontSize: Double): Double
    abstract fun calcLowercaseHeight(fontSize: Double): Double
    abstract fun calcLetterSpacing(fontSize: Double): Double
    abstract fun calcMinLineHeight(fontSize: Double): Double
    abstract fun calcMinWordSpacing(fontSize: Double): Double
    abstract fun calcFontWeight(fontSize: Double): Double
}