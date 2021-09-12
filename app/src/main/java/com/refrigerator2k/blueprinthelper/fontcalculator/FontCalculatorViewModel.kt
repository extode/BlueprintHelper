package com.refrigerator2k.blueprinthelper.fontcalculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.refrigerator2k.blueprinthelper.FontProperties
import com.refrigerator2k.blueprinthelper.FontProvider

class FontCalculatorViewModel : ViewModel() {
    companion object {
        const val NO_FONT_SELECTED = -1
    }

    private val fontCalculator: FontCalculator = FontCalculatorTypeB()
    private var _fontSizeStr = ""
    private val font = FontProvider.font

    val fontSizeStr: String
        get() = _fontSizeStr

    init {
        _fontSizeStr = font.properties.size.toString()
    }


    // default fonts

    private val _defaultFontSizes = MutableLiveData<List<Double>>(
        listOf(1.8, 2.5, 3.5, 5.0, 7.0, 10.0, 14.0, 20.0)
    )
    val defaultFontSizes: LiveData<List<Double>> = _defaultFontSizes

    private val _selectedFontIndex = MutableLiveData<Int>(-1)
    val selectedFontIndex: LiveData<Int> = _selectedFontIndex

    private val _unselectedFontIndex = MutableLiveData<Int>(-1)
    val unselectedFontIndex: LiveData<Int> = _unselectedFontIndex


    // font properties
    private var _fontProperties = MutableLiveData<FontProperties>()
    val fontProperties: LiveData<FontProperties> = _fontProperties

    /*private var _uppercaseLetterHeight = MutableLiveData<Double>()
    val uppercaseLetterHeight: LiveData<Double> = _uppercaseLetterHeight

    private var _lowercaseLetterHeight = MutableLiveData<Double>()
    val lowercaseLetterHeight: LiveData<Double> = _lowercaseLetterHeight

    private var _letterSpacing = MutableLiveData<Double>()
    val letterSpacing: LiveData<Double> = _letterSpacing

    private var _minLineHeight = MutableLiveData<Double>()
    val minLineHeight: LiveData<Double> = _minLineHeight

    private var _minWordSpacing = MutableLiveData<Double>()
    val minWordSpacing: LiveData<Double> = _minWordSpacing

    private var _fontLineWeight = MutableLiveData<Double>()
    val fontLineWeight: LiveData<Double> = _fontLineWeight*/

    fun setFontSize(fontSizeStr: String) {
        _fontSizeStr = fontSizeStr
        val fontSize = fontSizeStr.toDoubleOrNull()

        if (fontSize != null) {
            font.properties = fontCalculator.calculate(fontSize)
            _fontProperties.value = font.properties
            /*_uppercaseLetterHeight.value = params.uppercaseHeight
            _lowercaseLetterHeight.value = params.lowercaseHeight
            _letterSpacing.value = params.letterSpacing
            _minLineHeight.value = params.minLineHeight
            _minWordSpacing.value = params.minWordSpacing
            _fontLineWeight.value = params.fontWeight*/
        }

        var fontIndex = _defaultFontSizes.value!!.indexOf(fontSize)
        if (fontIndex == -1)
            fontIndex = NO_FONT_SELECTED
        selectFontWithIndex(fontIndex)
    }

    private fun selectFontWithIndex(index: Int) {
        _unselectedFontIndex.value = _selectedFontIndex.value
        _selectedFontIndex.value = index
    }
}