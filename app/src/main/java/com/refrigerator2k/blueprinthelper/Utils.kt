package com.refrigerator2k.blueprinthelper

import android.text.Editable
import android.text.TextWatcher
import kotlin.math.round

fun round2(d: Double) = round(d * 100.0) / 100.0

fun simpleTextWatcher(onChanged: (text: CharSequence) -> Unit) = object : TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        onChanged(s ?: "")
    }

    override fun afterTextChanged(s: Editable?) {}
}
