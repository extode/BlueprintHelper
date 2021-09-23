package com.refrigerator2k.blueprinthelper.fontcalculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.refrigerator2k.blueprinthelper.FontProperties
import com.refrigerator2k.blueprinthelper.FontProvider
import com.refrigerator2k.blueprinthelper.ktx.toPrettyString
import com.refrigerator2k.blueprinthelper.round2

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
        _fontSizeStr = font.properties.size.toPrettyString()
    }


    // default fonts
    private val _defaultFontSizes = MutableLiveData<List<Double>>(
        listOf(1.8, 2.5, 3.5, 5.0, 7.0, 10.0, 14.0, 20.0)
    )
    val defaultFontSizes: LiveData<List<Double>> = _defaultFontSizes

    private val _selectedFontIndex = MutableLiveData<Int>(NO_FONT_SELECTED)
    val selectedFontIndex: LiveData<Int> = _selectedFontIndex

    private val _unselectedFontIndex = MutableLiveData<Int>(NO_FONT_SELECTED)
    val unselectedFontIndex: LiveData<Int> = _unselectedFontIndex


    // font properties
    private var _fontProperties = MutableLiveData<FontProperties>()
    val fontProperties: LiveData<FontProperties> = _fontProperties

    val uppercaseHeightStr: LiveData<String> = Transformations.map(_fontProperties) {
        double2size(it.uppercaseHeight)
    }

    val lowercaseHeightStr: LiveData<String> = Transformations.map(_fontProperties) {
        double2size(it.lowercaseHeight)
    }

    val letterSpacingStr: LiveData<String> = Transformations.map(_fontProperties) {
        double2size(it.letterSpacing)
    }

    val minLineHeightStr: LiveData<String> = Transformations.map(_fontProperties) {
        double2size(it.minLineHeight)
    }

    val minWordSpacingStr: LiveData<String> = Transformations.map(_fontProperties) {
        double2size(it.minWordSpacing)
    }

    val minFontWeightStr: LiveData<String> = Transformations.map(_fontProperties) {
        double2size(it.fontWeight)
    }

    fun setFontSize(fontSizeStr: String) {
        _fontSizeStr = fontSizeStr
        val fontSize = fontSizeStr.toDoubleOrNull()

        if (fontSize != null) {
            font.properties = fontCalculator.calculate(fontSize)
            _fontProperties.value = font.properties
        }

        var fontIndex = _defaultFontSizes.value!!.indexOf(fontSize)
        if (fontIndex == -1)
            fontIndex = NO_FONT_SELECTED
        selectFontWithIndex(fontIndex)
    }

    private fun selectFontWithIndex(index: Int) {
        if (_selectedFontIndex.value != index) {
            _unselectedFontIndex.value = _selectedFontIndex.value
            _selectedFontIndex.value = index
        }
    }

    fun deselectionHandled() {
        _unselectedFontIndex.value = NO_FONT_SELECTED
    }

    private fun double2size(value: Double): String {
        return round2(value).toPrettyString()
    }
}