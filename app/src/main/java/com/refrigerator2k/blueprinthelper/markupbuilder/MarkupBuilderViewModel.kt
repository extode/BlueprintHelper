package com.refrigerator2k.blueprinthelper.markupbuilder

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.refrigerator2k.blueprinthelper.FontProvider
import com.refrigerator2k.blueprinthelper.LetterWidth

class MarkupBuilderViewModel : ViewModel() {
    private var _text = ""

    private val _markup = MutableLiveData<List<Letter>>()
    val markup: LiveData<List<Letter>> = _markup

    private val _markupWidth = MutableLiveData<Float>()
    val markupWidth: LiveData<Float> = _markupWidth

    private var _areaWidth = 0.0f

    private val _noSuchLetterEvent = MutableLiveData<Char>()
    val noSuchLetterEvent: LiveData<Char> = _noSuchLetterEvent

    private val _showMarkupInfoEvent = MutableLiveData<Boolean>(false)
    val showMarkupInfoEvent: LiveData<Boolean> = _showMarkupInfoEvent

    private val _markupDoesNotFitToTheAreaEvent = MutableLiveData<Boolean>(false)
    val markupDoesNotFitToTheAreaEvent: LiveData<Boolean> = _markupDoesNotFitToTheAreaEvent

    fun setText(text: String) {
        if (_text == text)
            return

        _text = text
        val letters = mutableListOf<Letter>()

        var offset = 0.0
        var letterW: Double? = 0.0
        val letterSpacing = FontProvider.font.properties.letterSpacing
        for (c in text) {
            letterW = LetterWidth[c]
            if (letterW == null) {
                _noSuchLetterEvent.value = c
                return
            }

            if (c != ' ') {
                val b = offset
                val e = offset + letterW
                letters.add(Letter(c, b, e))
            }

            offset += letterW + letterSpacing
        }

        _markupWidth.value = (offset - letterSpacing).toFloat()
        _markup.value = letters

        if (_markupWidth.value!! > _areaWidth) {
            _markupDoesNotFitToTheAreaEvent.value = true
        }
    }

    fun setAreaWidth(w: Float) {
        _areaWidth = w
    }

    fun infoPressed() {
        _showMarkupInfoEvent.value = true
    }

    fun noSuchLetterEventHandled() {
        _noSuchLetterEvent.value = null
    }

    fun showMarkupInfoEventHandled() {
        _showMarkupInfoEvent.value = false
    }

    fun markupDoesNotFitToTheAreaEventHandled() {
        _markupDoesNotFitToTheAreaEvent.value = false
    }
}