package com.refrigerator2k.blueprinthelper.fontcalculator

class FontCalculatorFactory {
    companion object {
        fun create(id: Int): FontCalculator {
            //when (id) {
            //    0 -> FontCalculatorTypeB()
            //}
            return FontCalculatorTypeB()
        }
    }
}