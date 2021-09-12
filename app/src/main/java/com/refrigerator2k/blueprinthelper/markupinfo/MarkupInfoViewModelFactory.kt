package com.refrigerator2k.blueprinthelper.markupinfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class MarkupInfoViewModelFactory(val text: String, val areaWidth: Float, val markupWidth: Float) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MarkupInfoViewModel::class.java)) {
            return MarkupInfoViewModel(text, areaWidth, markupWidth) as T
        }
        throw IllegalArgumentException()
    }
}