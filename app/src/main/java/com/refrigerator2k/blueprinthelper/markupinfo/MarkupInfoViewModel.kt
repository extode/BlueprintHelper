package com.refrigerator2k.blueprinthelper.markupinfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MarkupInfoViewModel(text: String, sheetWidth: Float, markupWidth: Float) : ViewModel() {
    private val _processedText = MutableLiveData<String>(text)
    val processedText: LiveData<String> = _processedText

    private val _charsCount = MutableLiveData<Int>(text.length)
    val charsCount: LiveData<Int> = _charsCount

    private val _areaWidth = MutableLiveData<Float>(sheetWidth)
    val areaWidth: LiveData<Float> = _areaWidth

    private val _markupWidth = MutableLiveData<Float>(markupWidth)
    val markupWidth: LiveData<Float> = _markupWidth

    private val _marginFromTheEdge = MutableLiveData<Float>()
    val marginFromTheEdge: LiveData<Float> = _marginFromTheEdge

    init {
        _marginFromTheEdge.value = (sheetWidth - markupWidth) / 2.0f
    }
}
