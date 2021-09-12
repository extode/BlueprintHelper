package com.refrigerator2k.blueprinthelper.markupbuilder

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.refrigerator2k.blueprinthelper.FontProvider
import com.refrigerator2k.blueprinthelper.LetterWidth

class MarkupBuilderViewModel : ViewModel() {
    private val _markup = MutableLiveData<List<Letter>>()
    val markup: LiveData<List<Letter>> = _markup

    private val _markupWidth = MutableLiveData<Float>()
    val markupWidth: LiveData<Float> = _markupWidth

    private val _noSuchLetterEvent = MutableLiveData<Char>()
    val noSuchLetterEvent: LiveData<Char> = _noSuchLetterEvent

    private val _showMarkupInfoEvent = MutableLiveData<Boolean>(false)
    val showMarkupInfoEvent: LiveData<Boolean> = _showMarkupInfoEvent

    fun setText(text: String) {
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
}